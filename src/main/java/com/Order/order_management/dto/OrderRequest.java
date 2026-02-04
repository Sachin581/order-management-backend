package com.Order.order_management.dto;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
    private Long id ;
    private Integer quantity;

}
