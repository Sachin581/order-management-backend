package com.Order.order_management.service;

import com.Order.order_management.dto.RequestLogin;
import com.Order.order_management.dto.ResponseLogin;
import com.Order.order_management.entity.User;
import com.Order.order_management.exception.InvalidCredentialsException;
import com.Order.order_management.jwt.JwtUtil;
import com.Order.order_management.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository userRepository,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.jwtUtil=jwtUtil;
    }
    public ResponseLogin login(@Valid RequestLogin requestLogin) {
       Optional <User> temp = userRepository.findByEmail(requestLogin.getEmail());
       if(temp.isEmpty()){
           throw new InvalidCredentialsException("This Email Does not exist");
       }

        if(!requestLogin.getPassword().equals(temp.get().getPassword())){
            throw new InvalidCredentialsException("Password Not matches");
        }
        return new ResponseLogin(jwtUtil.generateToken(requestLogin.getEmail()));

    }
}
