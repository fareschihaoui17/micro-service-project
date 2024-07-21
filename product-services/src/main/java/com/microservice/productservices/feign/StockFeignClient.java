package com.microservice.productservices.feign;

import com.microservice.productservices.dto.StockDTO;
import com.microservice.productservices.exception.ProjectException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-services")
public interface StockFeignClient {

    @GetMapping("/api/stocks/{id}")
    StockDTO getStockById(@PathVariable Long id) throws ProjectException;

}
