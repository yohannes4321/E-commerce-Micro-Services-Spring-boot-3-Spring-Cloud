package com.example.Order.Order;

import org.springframework.stereotype.Service;

@Service
public class OrderLineService {
    private  OrderLineRepository orderLineRepository;
    private  OrderLineMapper orderLineMapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order=orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
