package com.iati.product.service.impl;

import com.iati.product.configuration.security.CustomUserDetail;
import com.iati.product.domain.User;
import com.iati.product.dto.user.RegisterUserResponse;
import com.iati.product.dto.user.RegisterUserCommand;
import com.iati.product.repository.UserRepository;
import com.iati.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetail(user);
    }

    /**
     * create a new user
     *
     * @param registerUserCommand contains username and password
     * @return RegisterUserResponse
     *
     */

    @Override
    public RegisterUserResponse register(RegisterUserCommand registerUserCommand) {

        User entity = new User();
        entity.setUsername(registerUserCommand.getUsername());
        entity.setPassword(passwordEncoder.encode(registerUserCommand.getPassword()));
        entity = userRepository.save(entity);

        return new RegisterUserResponse("asa", entity.getUsername());
    }

}
