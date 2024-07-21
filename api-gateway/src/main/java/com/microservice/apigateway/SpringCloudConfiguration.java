package com.microservice.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("stockModule", r -> r.path("/api/stocks/**")
                        .filters(f -> f.retry(retryConfig -> retryConfig
                                .setRetries(3)
                                .setStatuses(org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE)
                                .setMethods(org.springframework.http.HttpMethod.GET, org.springframework.http.HttpMethod.POST)))
                        .uri("lb://stock-services")
                )
                .route("productModule", r -> r.path("/api/products/**")
                        .filters(f -> f.retry(retryConfig -> retryConfig
                                .setRetries(3)
                                .setStatuses(org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE)
                                .setMethods(org.springframework.http.HttpMethod.GET, org.springframework.http.HttpMethod.POST)))
                        .uri("lb://product-services")
                )
                .build();
    }
}
