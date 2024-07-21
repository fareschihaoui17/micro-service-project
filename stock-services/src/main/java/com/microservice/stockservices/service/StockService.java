package com.microservice.stockservices.service;

import com.microservice.stockservices.dto.StockDTO;
import java.util.List;

public interface StockService {
    List<StockDTO> getAllStocks();
    StockDTO getStockById(Long id);
    StockDTO addStock(StockDTO stockDTO);
    StockDTO updateStock(Long id,StockDTO stockDTO);
    void deleteStock(Long id);
}
