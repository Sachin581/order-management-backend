package com.Order.order_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseRegister {
    private Long id ;
    private String username;
    private String message;

}
