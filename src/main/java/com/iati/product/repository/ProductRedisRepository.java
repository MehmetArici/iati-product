package com.iati.product.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.domain.Product;

import java.io.IOException;

public interface ProductRedisRepository {

    void add(String productId, Product product) throws JsonProcessingException;

    Product find(String productId) throws IOException;

    void remove(String productId);
}
