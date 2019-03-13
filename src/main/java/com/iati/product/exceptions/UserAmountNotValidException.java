package com.iati.product.exceptions;

public class UserAmountNotValidException extends Exception {

    private static final long serialVersionUID = -8312225222116108742L;

    public UserAmountNotValidException(String s) {
        super(s);
    }

    public UserAmountNotValidException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
