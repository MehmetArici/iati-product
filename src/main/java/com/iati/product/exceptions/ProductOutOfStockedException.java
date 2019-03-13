package com.iati.product.exceptions;

public class ProductOutOfStockedException extends Exception {

    private static final long serialVersionUID = -8312225222116108742L;

    public ProductOutOfStockedException(String s) {
        super(s);
    }

    public ProductOutOfStockedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}