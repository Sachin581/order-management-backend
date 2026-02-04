package com.Order.order_management.controller;

import com.Order.order_management.dto.RequestLogin;
import com.Order.order_management.dto.RequestRegister;
import com.Order.order_management.dto.ResponseLogin;
import com.Order.order_management.dto.ResponseRegister;
import com.Order.order_management.service.AuthService;
import com.Order.order_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    public AuthController(UserService userService,AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseRegister> register(
           @Valid @RequestBody RequestRegister request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@Valid @RequestBody
                                                   RequestLogin requestLogin){
         return ResponseEntity.status(HttpStatus.OK).body(authService.login(requestLogin));

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
}
