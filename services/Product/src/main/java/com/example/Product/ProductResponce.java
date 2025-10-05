package com.example.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponce (

        Integer id,
        String name,
        String description,
        double available_quantity,
        BigDecimal price,

        Integer categorid,
        String categoryName,
        String CategoryDescription

) {
}
