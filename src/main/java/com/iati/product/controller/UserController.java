package com.iati.product.controller;

import com.iati.product.converter.UserConverter;
import com.iati.product.dto.user.RegisterUserResponse;
import com.iati.product.dto.user.RegisterUserCommand;
import com.iati.product.dto.user.UserDto;
import com.iati.product.exceptions.EmptyFieldException;
import com.iati.product.exceptions.ServiceException;
import com.iati.product.exceptions.UserAlreadyExistException;
import com.iati.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping(value = {"/register"})
    public RegisterUserResponse register(@RequestBody RegisterUserCommand request) throws EmptyFieldException, ServiceException, UserAlreadyExistException {

        if (request.getUsername() == null || request.getUsername().isEmpty()){
            throw new EmptyFieldException("Username cannot be empty");
        }
        else if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new EmptyFieldException("Password cannot be empty");
        }
        else if(request.getPassword().length() < 6){
            throw new EmptyFieldException("Password length cannot be less then 6");
        }

        return userService.register(request);
    }

    @GetMapping(value = {"/authenticated/user"})
    public UserDto get() {
        return userConverter.convert(userService.getAuthenticatedUser());
    }
}
