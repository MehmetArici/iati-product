package com.iati.product.controller;

import com.iati.product.dto.product.BuyProductCommand;
import com.iati.product.repository.ProductRepository;
import com.iati.product.service.ProductService;
import com.iati.product.service.UserService;
import jdk.nashorn.internal.ir.annotations.Ignore;
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

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ProductController.class})
@AutoConfigureJsonTesters
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    UserService userService;

    @Test
    @WithMockUser
    public void testGetAllProductTest() throws Exception {
        MockHttpServletRequestBuilder request = get("/api/product/get/all")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk());
        Mockito.verify(productService).getAll();
    }

    @Test
    @WithMockUser
    public void testGetAllProductWithTypeTest() throws Exception {
        MockHttpServletRequestBuilder request = get("/api/product/get/all/testType")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk());
        Mockito.verify(productService).findAllByType("testType");
    }

    @Test
    @WithMockUser
    public void testGetSingleProductTest() throws Exception {
        String id = UUID.randomUUID().toString();
        MockHttpServletRequestBuilder request = get("/api/product/get/" + id)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk());
        Mockito.verify(productService).getSingleProduct(id);
    }

    @Test
    @WithMockUser
    @Ignore
    public void testBuyProductTest() throws Exception {
        String id = UUID.randomUUID().toString();

        BuyProductCommand requestPayload = new BuyProductCommand();
        requestPayload.setProductId(id);

        String req = JSONParser.parseJSON("{" +
                "    productId: " +id +
                "}").toString();


        MockHttpServletRequestBuilder request = post("/api/product/buy")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(req);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isOk());
        Mockito.verify(productService).buy(Mockito.any());
    }

}
