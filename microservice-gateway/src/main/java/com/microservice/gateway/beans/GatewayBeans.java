package com.microservice.gateway.beans;

import com.microservice.gateway.filters.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class GatewayBeans {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public AuthFilter authFilter(WebClient.Builder webClientBuilder) {
        return new AuthFilter(webClientBuilder);
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder
                .routes()
                .route(route -> route
                        .path("/msvc-iam/api/v1/users/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-iam")
                )
                .route(route -> route
                        .path("/msvc-iam/api/v1/roles/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-iam")
                )
                .route(route -> route
                        .path("/msvc-request/api/v1/request/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://msvc-request")
                )
                .route(route -> route
                        .path("/msvc-iam/api/v1/authentication/**")
                        .uri("lb://msvc-iam")
                )
                .build();
    }
}