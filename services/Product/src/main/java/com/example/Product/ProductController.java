package com.example.Product;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid Productrequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponce>> purchaseProducts(
            @RequestBody  List<ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponce> getProductById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(productService.findbyid(productId));

    } @GetMapping("/{All-product}")
    public ResponseEntity<List<ProductResponce>> AllProduct() {
        return ResponseEntity.ok(productService.findAllProduct());

    }

}
