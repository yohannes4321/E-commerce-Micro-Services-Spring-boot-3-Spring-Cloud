package com.example.Product;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositery extends JpaRepository<Product,Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
