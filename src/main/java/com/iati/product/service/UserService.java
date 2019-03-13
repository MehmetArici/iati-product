package com.iati.product.service;

import com.iati.product.domain.User;
import com.iati.product.dto.user.RegisterUserResponse;
import com.iati.product.dto.user.RegisterUserCommand;
import com.iati.product.exceptions.UserAlreadyExistException;

public interface UserService {

    RegisterUserResponse register(RegisterUserCommand userDTO) throws UserAlreadyExistException;

    User getAuthenticatedUser();

}
