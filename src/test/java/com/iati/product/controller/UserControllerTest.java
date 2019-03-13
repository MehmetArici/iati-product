package com.iati.product.controller;

import com.iati.product.repository.ProductRepository;
import com.iati.product.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {UserController.class})
@AutoConfigureJsonTesters
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    ProductRepository productRepository;

    @Test
    @WithMockUser
    public void testRegisterPasswordLengthException() throws Exception{

        String requestPayload = JSONParser.parseJSON("{" +
                "    username: 'test.user'," +
                "    password: '123'" +
                "}").toString();


        MockHttpServletRequestBuilder request = post("/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser
    public void testRegisterEmptyField() throws Exception{

        String requestPayload = JSONParser.parseJSON("{" +
                "    password: '123456'" +
                "}").toString();


        MockHttpServletRequestBuilder request = post("/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isBadRequest());



    }

    @Test
    @WithMockUser
    public void testRegister() throws Exception{
        String requestPayload = JSONParser.parseJSON("{" +
                "    'username': 'test.user'," +
                "    'password': 'password123'" +
                "}").toString();


        MockHttpServletRequestBuilder request = post("/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPayload);


        ResultActions result = mockMvc.perform(request);
        System.out.println(result);
        result.andExpect(status().isOk());

        Mockito.verify(userService).register(Mockito.any());


    }


}
