package com.microservice.productservices.service;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductAddResponse addProductInStock(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
}
