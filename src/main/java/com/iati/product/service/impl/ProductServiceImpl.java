package com.iati.product.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.converter.ProductConverter;
import com.iati.product.domain.Product;
import com.iati.product.domain.ProductStatus;
import com.iati.product.dto.product.BuyProductCommand;
import com.iati.product.dto.product.BuyProductResponse;
import com.iati.product.dto.product.ProductDto;
import com.iati.product.exceptions.ProductNotFoundException;
import com.iati.product.repository.ProductRedisRepository;
import com.iati.product.repository.ProductRepository;
import com.iati.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductRedisRepository productRedisRepository;

    @Override
    public List<ProductDto> findAllByType(String type) throws ProductNotFoundException {
        List<Product> products = productRepository.findAllByType(type);

        if (products == null || products.size() == 0) {
            throw new ProductNotFoundException("No such a product record is found in type of " + type);
        }

        return convertAllProduct(products);
    }

    @Override
    public List<ProductDto> getAll() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if (products == null || products.size() == 0) {
            throw new ProductNotFoundException("No such a product record is found");
        }
        return convertAllProduct(products);
    }

    @Override
    public ProductDto getSingleProduct(String id) throws ProductNotFoundException, IOException {
        return getProduct(id);
    }

    @Override
    public BuyProductResponse buy(BuyProductCommand request) throws ProductNotFoundException, IOException {
        ProductDto product = getProduct(request.getProductId());

        if (product.getStatus() == ProductStatus.STOCKED) {

            product.setStatus(ProductStatus.SOLD);
            product.setQuantity(product.getQuantity() - 1);
        }

        return new BuyProductResponse(true, "Product sold.");
    }

    private List<ProductDto> convertAllProduct(List<Product> products) {
        return products
                .stream()
                .map(entity -> {
                    ProductDto dto = productConverter.convert(entity);
                    try {
                        productRedisRepository.add(dto.getId(), dto);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private ProductDto getProduct(String id) throws ProductNotFoundException, IOException {
        ProductDto productFromCache = productRedisRepository.find(id);
        if (productFromCache != null) {
            System.out.println("Founded in cache");
            return productFromCache;
        }
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(String.format("No such a product is found by id: %s ", id));
        }
        return productConverter.convert(product.get());
    }
}
