package com.microservice.productservices.resttemplate;

import com.microservice.productservices.dto.StockDTO;
import com.microservice.productservices.exception.ProjectException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class StockService {

    private final RestTemplate restTemplate;

    public StockDTO getStockById(Long id) throws ProjectException {
        var url = UriComponentsBuilder
                .fromUriString("http://localhost:8081/api/stocks/{id}")
                .buildAndExpand(id)
                .toUriString();
        
        try {
            return restTemplate.getForObject(url, StockDTO.class);
        } catch (Exception e) {
            throw new ProjectException(HttpStatus.NOT_FOUND,"Failed to fetch stock data");
        }
    }
}
