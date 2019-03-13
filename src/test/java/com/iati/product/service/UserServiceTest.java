package com.iati.product.service;

import com.iati.product.ProductApplication;
import com.iati.product.dto.user.RegisterUserCommand;
import com.iati.product.dto.user.RegisterUserResponse;
import com.iati.product.exceptions.UserAlreadyExistException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    RegisterUserCommand user(){
        RegisterUserCommand command = new RegisterUserCommand();
        command.setUsername("test.user");
        command.setPassword("mock.password");
        return command;
    }

    RegisterUserCommand user2(){
        RegisterUserCommand command = new RegisterUserCommand();
        command.setUsername("test.user2");
        command.setPassword("mock.password");
        return command;
    }

    @Test
    public void testRegisterSuccess(){

        try {
            RegisterUserResponse response = userService.register(user());
            assertEquals(response.getStatus(), "success");
        }
        catch (Exception e) {
            fail("Not registered");
        }
    }

    @Test
    public void testRegisterUsernameConflict(){

        try {
            userService.register(user2());
            userService.register(user2());

        }
        catch (Exception e) {
            assertTrue(e instanceof UserAlreadyExistException);
        }
    }

}
