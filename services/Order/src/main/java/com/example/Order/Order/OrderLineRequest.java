package com.example.Order.Order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public record OrderLineRequest(
        Integer id,
        Integer orderid,
        Integer productId,
        double quantity
) {

}
