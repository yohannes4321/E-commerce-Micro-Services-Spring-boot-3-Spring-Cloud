package com.example.Order.Order;

import com.example.Order.Customer.CustomerClient;
import com.example.Order.Product.ProductClient;
import com.example.Order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepositery repositery;
    private CustomerClient customerClient;
    private ProductClient productClient;
    private OrderMapper mapper;
    public Integer create(OrderRequest request) {
        var customer=this.customerClient.findCustomerById(request.customerid())
                .orElseThrow(()-> new BusinessException("Cannot create order No custmer exist"+ request.customerid()));

        //check the customer --> open Feign
        this.productClient.purchaseProducts(request.products());
        var order=this.repositery.save(mapper.toOrder(request));
        for ()
    }
}
