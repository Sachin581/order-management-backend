package com.Order.order_management.service;

import com.Order.order_management.dto.RequestRegister;
import com.Order.order_management.dto.ResponseRegister;
import com.Order.order_management.entity.Role;
import com.Order.order_management.entity.User;
import com.Order.order_management.exception.UserAlreadyExistsException;
import com.Order.order_management.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   private final UserRepository userRepository;
   public UserService(UserRepository userRepository){
       this.userRepository=userRepository;
   }
 public ResponseRegister register(RequestRegister requestRegister){
       if(userRepository.existsByEmail(requestRegister.getEmail())){
           throw new UserAlreadyExistsException("Email Already registered");

       }
      if(userRepository.existsByUsername(requestRegister.getUsername())){
         throw new UserAlreadyExistsException("username Already exist");
      }
     User user = new User();
      user.setUsername(requestRegister.getUsername());
      user.setEmail(requestRegister.getEmail());
      user.setPassword(requestRegister.getPassword());
      user.setRole(Role.USER);

      return new ResponseRegister(user.getId(), user.getUsername(),"Succesfully user registered");


 }


}
