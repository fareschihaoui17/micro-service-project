package com.microservice.productservices.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductAddResponse {
    private Long id;
    private String name;
    private Double price;
    private StockDTO stockDTO;
}
