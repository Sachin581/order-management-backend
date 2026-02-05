package com.Order.order_management.controller;


import com.Order.order_management.dto.OrderRequest;
import com.Order.order_management.dto.OrderResponse;
import com.Order.order_management.entity.Order;
import com.Order.order_management.service.OrderService;
import com.Order.order_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final OrderService orderService;
    public UserController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> Order(@RequestBody OrderRequest orderRequest){

        log.info("Api Point working");
        return orderService.order(orderRequest);
    }
    @GetMapping("/orderList")
    public ResponseEntity<Order>listOrder(){
        return orderService.listOrder();
    }

}
