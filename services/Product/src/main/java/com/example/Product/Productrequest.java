package com.example.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record Productrequest(
        @NotNull(message = "Product name is required ")
        Integer id,
        @NotNull(message =" Product name is required")
        String name,
        @NotNull(message = "Product description is required ")
        String description,
        @Positive@NotNull(message = "Available quantity should be postive")

        double available_quantity,
        @Positive@NotNull(message = "Available price should be postive")
        BigDecimal price,

        Integer categorid
) {
}
