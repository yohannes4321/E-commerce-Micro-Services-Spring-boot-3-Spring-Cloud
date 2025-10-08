package com.example.Order.Order;

import com.example.Order.Customer.CustomerClient;
import com.example.Order.Order.Kafka.OrderConfirmation;
import com.example.Order.Order.Kafka.OrderProducer;
import com.example.Order.Product.ProductClient;
import com.example.Order.Product.PurchaseRequest;
import com.example.Order.exception.BusinessException;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderProducer orderProducer;
    private OrderRepositery repositery;
    private CustomerClient customerClient;
    private ProductClient productClient;
    private OrderMapper mapper;
    private OrderLineService orderLineService;
    public Integer create(OrderRequest request) {
        var customer=this.customerClient.findCustomerById(request.customerid())
                .orElseThrow(()-> new BusinessException("Cannot create order No custmer exist"+ request.customerid()));

        //check the customer --> open Feign
        var purchaseproduct=this.productClient.purchaseProducts(request.products());
        var order=this.repositery.save(mapper.toOrder(request));
        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getOrderid(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity()

                    ));

        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.Price(),
                        request.paymentMethod(),
                        customer,
                        purchaseproduct


                ) );
        return order.getOrderid();
    }


}
