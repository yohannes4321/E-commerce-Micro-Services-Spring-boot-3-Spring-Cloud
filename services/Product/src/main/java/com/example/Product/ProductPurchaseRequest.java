package com.example.Product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productid,
        @NotNull(message ="product Quantity is mandaoty ")
        double quantity
) {
}
