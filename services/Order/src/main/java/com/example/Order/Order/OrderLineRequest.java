package com.example.Order.Order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderLineRequest {
    public OrderLineRequest(Integer id,
                            Integer orderid,
                            Integer productId,
                            double quantity) {

    }
}
