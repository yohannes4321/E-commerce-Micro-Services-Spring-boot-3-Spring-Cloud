package com.example.Order.Order;

import com.example.Order.Product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@Validated
public record OrderRequest(
        Integer orderid,
        String reference,
        @Positive(message = "Order amount should be postive")
        BigDecimal Price,
        @NotNull(message = "Payment should be present ")
        PaymentMethod paymentMethod,

        String customerid,
        @NotEmpty(message = "You should purchase one product")
        List<PurchaseRequest> products
) {

}
