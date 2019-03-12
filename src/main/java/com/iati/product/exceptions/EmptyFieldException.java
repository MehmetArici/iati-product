package com.iati.product.exceptions;

public class EmptyFieldException extends Exception{

    private static final long serialVersionUID = 340700715192669612L;

    public EmptyFieldException(String s) {
        super(s);
    }

    public EmptyFieldException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
