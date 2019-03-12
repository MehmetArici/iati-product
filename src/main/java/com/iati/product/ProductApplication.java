package com.iati.product;

import com.iati.product.domain.Product;
import com.iati.product.domain.ProductStatus;
import com.iati.product.dto.user.RegisterUserCommand;
import com.iati.product.repository.ProductRepository;
import com.iati.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private void mockProduct(final ProductRepository productRepository, final UserService userService) {

        try {

            Product product = new Product();
            product.setName("testProduct1");
            product.setStatus(ProductStatus.STOCKED);
            product.setQuantity(1);
            product.setDiscounted(false);
            product.setType("type1");
            product.setProvider("testProvider");
            product.setPrice(14);
            product.setDescription("mock product");

            productRepository.save(product);

            Product product2 = new Product();
            product2.setName("testProduct2");
            product2.setStatus(ProductStatus.STOCKED);
            product2.setQuantity(34);
            product2.setDiscounted(true);
            product2.setType("type2");
            product2.setProvider("testProvider");
            product2.setPrice(22);
            product2.setDescription("mock product2");
            productRepository.save(product2);


            RegisterUserCommand userDTO = new RegisterUserCommand();
            userDTO.setUsername("mock.user");
            userDTO.setPassword("mock.password");
            userService.register(userDTO);


            userDTO = new RegisterUserCommand();
            userDTO.setUsername("mock.user2");
            userDTO.setPassword("mock.password");
            userService.register(userDTO);
        }
        catch (Exception e){

        }


    }

}
