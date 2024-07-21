package com.microservice.productservices.service;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.exception.ProjectException;
import com.microservice.productservices.feign.StockFeignClient;
import com.microservice.productservices.mapper.ProductMapper;
import com.microservice.productservices.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final StockFeignClient stockFeignClient;
    private final ProductMapper productMapper;
    private static final String STOCK_NOT_FOUND ="Stock not found";

    @Override
    public ProductAddResponse addProductInStock(ProductDTO productDTO) {
        var stock = stockFeignClient.getStockById(productDTO.getStockId());
        if (stock == null) {
            throw new ProjectException(HttpStatus.NOT_FOUND,STOCK_NOT_FOUND);
        }
        var productJpa = productRepository.save(productMapper.fromProductDtoToProduct(productDTO));
        return ProductAddResponse.builder()
                .id(productJpa.getId())
                .name(productJpa.getName())
                .price(productJpa.getPrice())
                .stockDTO(stock)
                .build();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream().map(productMapper::fromProductToProductDto).toList();
    }
}
