package com.example.Order.Order.Kafka;

import com.example.Order.Customer.CustomerResponse;
import com.example.Order.Order.PaymentMethod;
import com.example.Order.Product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal orderAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> purchaseResponses
) {


}
