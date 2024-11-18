package com.microservice.restaurant.Interface.rest;

import com.microservice.restaurant.Interface.rest.resources.AccountResource;
import com.microservice.restaurant.Interface.rest.resources.CreateAccountResource;
import com.microservice.restaurant.Interface.rest.resources.UpdateAccountResource;
import com.microservice.restaurant.Interface.rest.transform.AccountResourceFromEntityAssembler;
import com.microservice.restaurant.Interface.rest.transform.CreateAccountCommandFromResourceAssembler;
import com.microservice.restaurant.domain.model.commands.DeleteAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountCommand;
import com.microservice.restaurant.domain.model.entities.Account;
import com.microservice.restaurant.domain.model.queries.GetAccountByIdQuery;
import com.microservice.restaurant.domain.model.queries.GetAllAccountsByRestaurantIdQuery;
import com.microservice.restaurant.domain.services.AccountCommandService;
import com.microservice.restaurant.domain.services.AccountQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Account", description = "Account Management Endpoints")
public class AccountController {
    private final AccountQueryService accountQueryService;
    private final AccountCommandService accountCommandService;
    public AccountController(AccountQueryService accountQueryService, AccountCommandService accountCommandService) {
        this.accountQueryService = accountQueryService;
        this.accountCommandService = accountCommandService;
    }

    @PostMapping
    public ResponseEntity<AccountResource> createMaterial(@RequestBody CreateAccountResource createAccountResource) {
        Optional<Account> accountSource = accountCommandService
                .handle(CreateAccountCommandFromResourceAssembler.toCommand(createAccountResource));
        return accountSource.map(source ->
                        new ResponseEntity<>(AccountResourceFromEntityAssembler.toResource(source), CREATED)).
                orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<AccountResource>> getAllAccountsByRestaurantId(@PathVariable Long restaurantId) {
        List<Account> accountSource = accountQueryService.handle(new GetAllAccountsByRestaurantIdQuery(restaurantId));
        var accountResource = accountSource.stream().map(AccountResourceFromEntityAssembler::toResource).toList();
        return new ResponseEntity<>(accountResource, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResource> getAccountById(@PathVariable Long id) {
        Optional<Account> materialSource = accountQueryService.handle(new GetAccountByIdQuery(id));
        return materialSource.map(source -> ResponseEntity.ok(AccountResourceFromEntityAssembler.toResource(source)))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResource> updateMaterial(@PathVariable Long id, @RequestBody UpdateAccountResource updateAccountResource) {
        UpdateAccountCommand command = new UpdateAccountCommand(
                id,
                updateAccountResource.accountName(),
                updateAccountResource.tableId(),
                updateAccountResource.restaurantId(),
                updateAccountResource.state(),
                updateAccountResource.totalAccount(),
                updateAccountResource.dateCreated(),
                updateAccountResource.dateLog(),
                updateAccountResource.products()
        );
        Optional<Account> materialsSource = accountCommandService.handle(command);
        return materialsSource.map(source ->
                        new ResponseEntity<>(AccountResourceFromEntityAssembler
                                .toResource(source), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        DeleteAccountCommand command = new DeleteAccountCommand(id);
        accountCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
