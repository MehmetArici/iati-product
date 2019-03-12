package com.iati.product.exceptions;

public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = -8312225222116108742L;

    public ProductNotFoundException(String s) {
        super(s);
    }

    public ProductNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
