package com.Order.order_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class RequestRegister {

    private String username ;
    private String password ;
    private String email;
}
