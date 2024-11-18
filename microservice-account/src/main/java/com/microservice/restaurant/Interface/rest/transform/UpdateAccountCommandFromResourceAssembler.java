package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.commands.UpdateAccountCommand;
import com.microservice.restaurant.domain.model.valueobjects.State;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateAccountCommandFromResourceAssembler {
    public static UpdateAccountCommand toCommand(
            Long id,
            String accountName,
            Long tableId,
            Long restaurantId,
            State state,
            Float totalAccount,
            LocalDateTime dateCreated,
            LocalDateTime dateLog,
            List<AccountProduct> products
    ){
        return new UpdateAccountCommand(id, accountName, tableId, restaurantId, state, totalAccount, dateCreated, dateLog, products);
    }
}
