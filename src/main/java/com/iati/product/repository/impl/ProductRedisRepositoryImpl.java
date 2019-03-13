package com.iati.product.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.configuration.cache.AbstractRedisRepository;
import com.iati.product.domain.Product;
import com.iati.product.repository.ProductRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

import static com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonObjectMapper;

@Component
public class ProductRedisRepositoryImpl extends AbstractRedisRepository<String, String> implements ProductRedisRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final long DURATION_TIME = 10L;

    // data duration on redis in minute
    @Override
    protected Duration duration() {
        return Duration.ofMinutes(DURATION_TIME);
    }

    /**
     * Add given product to redis with key=productId and value=product
     * @param productId
     * @param product
     * @throws JsonProcessingException
     */
    @Override
    public void add(String productId, Product product) throws JsonProcessingException {
        logger.info(String.format("PRODUCT ADDED TO REDIS WITH KEY %s AND BODY %s", productId, jacksonObjectMapper().writeValueAsString(product)));
        putValue(productId, jacksonObjectMapper().writeValueAsString(product));
    }

    /**
     * find product from redis with key=productId
     * @param productId
     * @return
     * @throws IOException
     */
    @Override
    public Product find(String productId) throws IOException {
        String productString = findValue(productId);
        logger.info(String.format("PRODUCT FOUNDED FROM REDIS WITH KEY %s AND BODY %s", productId, productString));
        return jacksonObjectMapper().readValue(productString, Product.class);
    }

    /**
     * remove product from redis with given key=productId
     * @param productId
     */
    @Override
    public void remove(String productId) {
        logger.info(String.format("PRODUCT REMOVED FROM REDIS WITH KEY %s", productId));
        delete(productId);
    }

}
