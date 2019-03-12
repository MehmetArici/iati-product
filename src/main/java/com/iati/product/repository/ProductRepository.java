package com.iati.product.repository;

import com.iati.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByType(String type);

    List<Product> findAll();

    Optional<Product> findById(String  id);

}
