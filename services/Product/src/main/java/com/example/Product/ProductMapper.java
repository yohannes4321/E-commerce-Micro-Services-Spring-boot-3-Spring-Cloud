package com.example.Product;

import com.example.Category.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(Productrequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .category(Category.builder().id(request.categorid()).build())
                .available_quantity(request.available_quantity())
                .price((request.price())).build();
    }

    public ProductResponce toProductResponce( Product product) {
        return new ProductResponce(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailable_quantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );

    }

    public ProductPurchaseResponce toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponce(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity

        );
    }
}
