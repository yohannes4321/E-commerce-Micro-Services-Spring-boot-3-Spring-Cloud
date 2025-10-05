package com.example.Product;

import java.math.BigDecimal;

public record ProductPurchaseResponce(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
