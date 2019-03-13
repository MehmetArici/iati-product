package com.iati.product.exceptions.handler;

import com.iati.product.exceptions.EmptyFieldException;
import com.iati.product.exceptions.ProductNotFoundException;
import com.iati.product.exceptions.ServiceException;
import com.iati.product.exceptions.UserAlreadyExistException;
import com.iati.product.exceptions.message.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiMessage handleUsernameAlreadyExistException(UserAlreadyExistException exception){
        return new ApiMessage(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiMessage handleDataNotFoundException(ProductNotFoundException ex){
        return new ApiMessage(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(value = {EmptyFieldException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiMessage handleEmptyFieldException(EmptyFieldException ex){
        return new ApiMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(HttpStatus.GONE)
    @ResponseBody
    public ApiMessage handleServiceException(ServiceException ex){
        return new ApiMessage(HttpStatus.GONE, ex.getMessage());
    }

}
