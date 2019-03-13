package com.iati.product.controller;

import com.iati.product.dto.product.BuyProductCommand;
import com.iati.product.dto.product.BuyProductResponse;
import com.iati.product.dto.product.ProductDto;
import com.iati.product.exceptions.ProductNotFoundException;
import com.iati.product.exceptions.ProductOutOfStockedException;
import com.iati.product.exceptions.UserAmountNotValidException;
import com.iati.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/product/"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"all"})
    public List<ProductDto> getAll() throws ProductNotFoundException {
        return productService.getAll();
    }

    @GetMapping(value = {"all/{type}"})
    public List<ProductDto> getAll(@PathVariable String type) throws ProductNotFoundException {
        return productService.findAllByType(type);
    }

    @GetMapping(value = {"{id}"})
    public ProductDto get(@PathVariable String id) throws ProductNotFoundException, IOException {
        return productService.getSingleProduct(id);
    }

    @PostMapping(value = {"buy"})
    public BuyProductResponse buy(@RequestBody BuyProductCommand request) throws ProductNotFoundException, IOException, ProductOutOfStockedException, UserAmountNotValidException {
        return productService.buy(request);
    }
}
