package com.Order.order_management.repository;

import com.Order.order_management.entity.Inventory;
import com.Order.order_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional <Inventory> findByProduct(Product product);
}
