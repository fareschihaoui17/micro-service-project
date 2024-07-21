package com.microservice.stockservices.mapper;

import com.microservice.stockservices.dto.StockDTO;
import com.microservice.stockservices.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDTO fromStockToStockDTO(Stock stock);
    Stock fromStockDtoToStock(StockDTO stockDTO);
}
