package com.Order.order_management.dto;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestLogin {
    private String username;

    @Email(message = "Email is not Valid")
    private String email;

    private String password;
}
