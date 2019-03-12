package com.iati.product.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.dto.product.ProductDto;

import java.io.IOException;

public interface ProductRedisRepository {

    void add(String productId, ProductDto productDto) throws JsonProcessingException;

    ProductDto find(String productId) throws IOException;

    void remove(String productId);

    boolean exists(String productId);

}
