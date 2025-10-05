package com.example.Order.Product;

import java.math.BigDecimal;

public record PurchaseResponse(
Integer productid,
String name,
String description,
BigDecimal price,
double quantity
) {
}
