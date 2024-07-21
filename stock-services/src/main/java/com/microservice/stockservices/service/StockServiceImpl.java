package com.microservice.stockservices.service;

import com.microservice.stockservices.dto.StockDTO;
import com.microservice.stockservices.entity.Stock;
import com.microservice.stockservices.exception.ProjectException;
import com.microservice.stockservices.mapper.StockMapper;
import com.microservice.stockservices.repository.StockRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private static final String STOCK_NOT_FOUND ="Stock not found";
    @Override
    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream().map(stockMapper::fromStockToStockDTO).toList();
    }

    @Override
    public StockDTO getStockById(Long id) {
        return stockMapper.fromStockToStockDTO(getStockJpaById(id));
    }

    @Override
    public StockDTO addStock(StockDTO stockDTO) {
        return stockMapper.fromStockToStockDTO(stockRepository.save(stockMapper.fromStockDtoToStock(stockDTO)));
    }

    @Override
    public StockDTO updateStock(Long id, StockDTO stockDTO) {
        var stockJpa = getStockJpaById(id);
        stockJpa.setName(stockDTO.getName());
        stockRepository.save(stockJpa);
        return stockMapper.fromStockToStockDTO(stockJpa);
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.delete(getStockJpaById(id));
    }

    private Stock getStockJpaById(Long id) {
        return stockRepository.findById(id).orElseThrow(()->new ProjectException(HttpStatus.NOT_FOUND,STOCK_NOT_FOUND));
    }
}
