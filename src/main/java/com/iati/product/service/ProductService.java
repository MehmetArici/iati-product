package com.iati.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.dto.product.BuyProductCommand;
import com.iati.product.dto.product.BuyProductResponse;
import com.iati.product.dto.product.ProductDto;
import com.iati.product.exceptions.ProductNotFoundException;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductDto> findAllByType(String type) throws ProductNotFoundException;

    List<ProductDto> getAll() throws ProductNotFoundException;

    ProductDto getSingleProduct(String id) throws ProductNotFoundException, IOException;

    BuyProductResponse buy(BuyProductCommand request) throws ProductNotFoundException, IOException;

}
