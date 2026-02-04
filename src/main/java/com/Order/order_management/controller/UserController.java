package com.Order.order_management.controller;


import com.Order.order_management.dto.OrderRequest;
import com.Order.order_management.dto.OrderResponse;
import com.Order.order_management.service.OrderService;
import com.Order.order_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final OrderService orderService;
    public UserController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> Order(@RequestBody OrderRequest orderRequest){
        return orderService.order(orderRequest);
    }

}
