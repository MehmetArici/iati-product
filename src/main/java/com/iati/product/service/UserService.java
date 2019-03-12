package com.iati.product.service;

import com.iati.product.dto.user.RegisterUserResponse;
import com.iati.product.dto.user.RegisterUserCommand;

public interface UserService {

    RegisterUserResponse register(RegisterUserCommand userDTO);

}
