package com.example.Order.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositery extends JpaRepository<Order, Integer> {
}
