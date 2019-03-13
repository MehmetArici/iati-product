package com.iati.product.converter;

import com.iati.product.domain.User;
import com.iati.product.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User content) {

        UserDto dto = new UserDto();
        dto.setUsername(content.getUsername());
        dto.setPassword(content.getPassword());
        dto.setAmount(content.getAmount());
        dto.setProducts(content.getProducts());

        return dto;
    }

}
