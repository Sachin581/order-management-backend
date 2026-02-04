package com.Order.order_management.repository;

import com.Order.order_management.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Long> {
}
