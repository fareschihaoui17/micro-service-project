package com.microservice.stockservices.controller;

import com.microservice.stockservices.dto.StockDTO;
import com.microservice.stockservices.service.StockService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stocks")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;
    @GetMapping("/allStocks")
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.addStock(stockDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStock(id,stockDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
