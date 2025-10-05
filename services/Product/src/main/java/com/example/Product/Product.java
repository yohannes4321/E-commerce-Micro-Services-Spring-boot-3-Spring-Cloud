package com.example.Product;

import com.example.Category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double available_quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
