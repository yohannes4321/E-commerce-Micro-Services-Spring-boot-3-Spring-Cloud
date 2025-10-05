package com.example.Product;

import com.example.Product.Exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private  ProductRepositery productRepositery;
    private  ProductMapper productMapper;
    public List<ProductPurchaseResponce> purchaseProducts(List<ProductPurchaseRequest> request) {
        //contain many product not one product

        var productid=request.stream()
                .map(ProductPurchaseRequest::productid)
                .toList();

        // this comes from the storage
        var storedProducts=productRepositery.findAllByIdInOrderById(productid);
        if(productid.size()!=storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exists");

        }
        // this comes  from request
        var storeRequest=request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productid))
                .toList();
        var purchasedProducts= new ArrayList<ProductPurchaseResponce>();
        for (int i=0; i< storedProducts.size();i++){
            var product=storedProducts.get(i);
            var productRequest=storeRequest.get(i);
            if (product.getAvailable_quantity()< productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient quantity for productID " + product.getId());

            }
            var newAvaliablequantity=product.getAvailable_quantity()-productRequest.quantity();
            product.setAvailable_quantity(newAvaliablequantity);
            productRepositery.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));

        }



        return null;
    }

    public ProductResponce findbyid(Integer productId) {
        return productRepositery.findById(productId)
                .map(productMapper:: toProductResponce)
                .orElseThrow(() -> new EntityNotFoundException(" Product not found with in ID" +productId));
    }

    public List<ProductResponce> findAllProduct() {
        return productRepositery.findAll()
                .stream().map(productMapper::toProductResponce)
                .collect(Collectors.toList());
    }

    public Integer createProduct( Productrequest request) {
    var product= productMapper.toProduct(request);
    return productRepositery.save(product).getId();

    }
    }


