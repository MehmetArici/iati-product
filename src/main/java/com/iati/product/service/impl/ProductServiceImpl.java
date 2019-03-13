package com.iati.product.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iati.product.converter.ProductConverter;
import com.iati.product.domain.Product;
import com.iati.product.domain.ProductStatus;
import com.iati.product.domain.User;
import com.iati.product.dto.product.BuyProductCommand;
import com.iati.product.dto.product.BuyProductResponse;
import com.iati.product.dto.product.ProductDto;
import com.iati.product.exceptions.ProductNotFoundException;
import com.iati.product.exceptions.ProductOutOfStockedException;
import com.iati.product.exceptions.UserAmountNotValidException;
import com.iati.product.repository.ProductRedisRepository;
import com.iati.product.repository.ProductRepository;
import com.iati.product.repository.UserRepository;
import com.iati.product.service.ProductService;
import com.iati.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductRedisRepository productRedisRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * get all product with given type
     * @param type
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public List<ProductDto> findAllByType(String type) throws ProductNotFoundException {
        List<Product> products = productRepository.findAllByType(type);

        if (products == null || products.size() == 0) {
            throw new ProductNotFoundException("No such a product record is found in type of " + type);
        }

        return convertAllProduct(products);
    }

    /**
     * get all product
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public List<ProductDto> getAll() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if (products == null || products.size() == 0) {
            throw new ProductNotFoundException("No such a product record is found");
        }
        return convertAllProduct(products);
    }

    /**
     * get single product with given product identifier
     * @param id
     * @return
     * @throws ProductNotFoundException
     * @throws IOException
     */
    @Override
    public ProductDto getSingleProduct(String id) throws ProductNotFoundException, IOException {
        return productConverter.convert(getProduct(id));
    }

    /**
     * Buy product with given product identifier and
     * update user account amount and product information
     * @param request
     * @return
     * @throws ProductNotFoundException
     * @throws IOException
     * @throws ProductOutOfStockedException
     * @throws UserAmountNotValidException
     */
    @Override
    public BuyProductResponse buy(BuyProductCommand request) throws ProductNotFoundException, IOException, ProductOutOfStockedException, UserAmountNotValidException {
        Product product = getProduct(request.getProductId());

        if (product.getStatus() == ProductStatus.STOCKED) {
            User authenticatedUser = userService.getAuthenticatedUser();
            if (authenticatedUser.getAmount() >= product.getPrice()) {
                product.setQuantity(product.getQuantity() - 1);
                productRepository.save(product);

                authenticatedUser.setAmount(authenticatedUser.getAmount() - product.getPrice());
                userRepository.save(authenticatedUser);

                if (product.getQuantity() == 0) {
                    productRedisRepository.remove(product.getId());
                    product.setStatus(ProductStatus.OUT_OF_STOCK);
                    productRepository.save(product);
                }

            }
            else {
                throw new UserAmountNotValidException("User amount is smaller than selected product price.");
            }
        }
        else {
            throw new ProductOutOfStockedException("Selected product to buy out of stocked of provider.");
        }

        return new BuyProductResponse(true, "Product sold successfully");
    }

    /**
     * convert product entity to product dto and
     * add founded stocked products to redis
     * @param products
     * @return
     */
    private List<ProductDto> convertAllProduct(List<Product> products) {
        return products
                .stream()
                .map(entity -> {
                    ProductDto dto = productConverter.convert(entity);
                    try {
                        if (dto.getStatus() == ProductStatus.STOCKED) {
                            productRedisRepository.add(entity.getId(), entity);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * get product from redis if exist
     * otherwise get from db
     * @param id
     * @return
     * @throws ProductNotFoundException
     * @throws IOException
     */
    private Product getProduct(String id) throws ProductNotFoundException, IOException {
        Product productFromCache = productRedisRepository.find(id);
        if (productFromCache != null) {
            return productFromCache;
        }

        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(String.format("No such a product is found by id: %s ", id));
        }
        return product.get();
    }
}
