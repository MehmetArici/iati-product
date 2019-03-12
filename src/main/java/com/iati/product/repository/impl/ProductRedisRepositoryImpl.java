package com.iati.product.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.configuration.cache.AbstractRedisRepository;
import com.iati.product.dto.product.ProductDto;
import com.iati.product.repository.ProductRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import static com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonObjectMapper;

@Component
public class ProductRedisRepositoryImpl extends AbstractRedisRepository<String, String> implements ProductRedisRepository {

    private final long DURATION_TIME = 10L;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected Duration duration() {
        return Duration.ofMinutes(DURATION_TIME);
    }

    @Override
    public void add(String productId, ProductDto productDto) throws JsonProcessingException {
        putValue(productId, jacksonObjectMapper().writeValueAsString(productDto));
    }

    @Override
    public ProductDto find(String productId) throws IOException {
        String productString = findValue(productId);
        return jacksonObjectMapper().readValue(productString, ProductDto.class);
    }

    @Override
    public void remove(String productId) {
        delete(productId);
    }

    @Override
    public boolean exists(String productId) {
        return false;
    }
}
