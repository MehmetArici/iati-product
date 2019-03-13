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
    private void mockProduct(final ProductRepository productRepository) {

        try {
            Product product = new Product();
            product.setName("Notebook Dell Alienware - i7 16GB");
            product.setImageUrl("https://images-submarino.b2w.io/produtos/01/00/sku/34470/9/34470934G1.jpg");
            product.setStatus(ProductStatus.STOCKED);
            product.setQuantity(1);
            product.setDiscounted(false);
            product.setType("notebook");
            product.setProvider("Teknosa");
            product.setPrice(2599);
            product.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s");

            productRepository.save(product);

            Product product2 = new Product();
            product2.setName("Notebook VAIO Fit 15S B1211B Intel Core i5 4GB");
            product2.setStatus(ProductStatus.STOCKED);
            product2.setQuantity(10);
            product2.setDiscounted(true);
            product2.setImageUrl("https://images-americanas.b2w.io/produtos/01/00/item/133252/7/133252789G1.jpg");
            product2.setType("notebook");
            product2.setProvider("Vatan");
            product2.setPrice(2100);
            product2.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s");
            productRepository.save(product2);

            Product product3 = new Product();
            product3.setName("Notebook Samsung Essentials E21 Intel Celeron Dual Core");
            product3.setStatus(ProductStatus.OUT_OF_STOCK);
            product3.setQuantity(0);
            product3.setDiscounted(false);
            product3.setImageUrl("https://images-americanas.b2w.io/produtos/01/00/item/132165/8/132165801G1.jpg");
            product3.setType("notebook");
            product3.setProvider("Teknosa");
            product3.setPrice(1500);
            product3.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s");
            productRepository.save(product3);

            Product product4 = new Product();
            product4.setName("Smartphone Xiaomi Mi A1 dual Android one 7.1");
            product4.setImageUrl("https://images-americanas.b2w.io/produtos/01/00/sku/29296/2/29296259G1.jpg");
            product4.setStatus(ProductStatus.STOCKED);
            product4.setQuantity(1);
            product4.setDiscounted(true);
            product4.setType("smartphone");
            product4.setProvider("Teknosa");
            product4.setPrice(1199);
            product4.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s");

            productRepository.save(product4);

            Product product5 = new Product();
            product5.setName("Smartphone Moto G 5S Dual Chip Android 7.0");
            product5.setStatus(ProductStatus.OUT_OF_STOCK);
            product5.setQuantity(0);
            product5.setDiscounted(false);
            product5.setImageUrl("https://images-americanas.b2w.io/produtos/01/00/item/132474/0/132474081G1.png");
            product5.setType("smartphone");
            product5.setProvider("Vatan");
            product5.setPrice(929);
            product5.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s");
            productRepository.save(product5);

            Product product6 = new Product();
            product6.setName("iPhone 8 Dourado 64GB Tela 4.7 IOS 11");
            product6.setStatus(ProductStatus.STOCKED);
            product6.setQuantity(2);
            product6.setDiscounted(false);
            product6.setImageUrl("https://images-americanas.b2w.io/produtos/01/00/item/132651/7/132651745G1.jpg");
            product6.setType("smartphone");
            product6.setProvider("Teknosa");
            product6.setPrice(6300);
            product6.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s");
            productRepository.save(product6);

        }
        catch (Exception e){

        }
    }

    @Autowired
    private void mockUser(final UserService userService) {
        try {
            RegisterUserCommand userDTO = new RegisterUserCommand();
            userDTO.setUsername("mock.user");
            userDTO.setPassword("mock.password");
            userDTO.setAmount(20000L);
            userService.register(userDTO);


            userDTO = new RegisterUserCommand();
            userDTO.setUsername("mock.user2");
            userDTO.setPassword("mock.password");
            userDTO.setAmount(10000L);
            userService.register(userDTO);
        }
        catch (Exception e) {

        }
    }

}
