package com.iati.product.exceptions;

public class UserAlreadyExistException extends Exception {

    private static final long serialVersionUID = -1547414878179356789L;

    public UserAlreadyExistException(String s) {
        super(s);
    }

}
