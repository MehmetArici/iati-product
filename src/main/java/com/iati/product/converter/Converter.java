package com.iati.product.converter;

public interface Converter<T, R> {

    R convert(T content);

}
