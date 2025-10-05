package com.example.Order.Order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request){
        return Order.builder()
                .orderid(request.orderid())
                .price(request.Price())
                .customerId(request.customerid())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())

                .build();
    }
}
