package com.iati.product.configuration.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRedisRepository<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    protected abstract Duration duration();

    protected void putValue(K key, V value) {
        redisTemplate.opsForValue().set(key, value, duration().getSeconds(), TimeUnit.SECONDS);
    }

    protected V findValue(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    protected void delete(K key) {
        redisTemplate.delete(key);
    }

}
