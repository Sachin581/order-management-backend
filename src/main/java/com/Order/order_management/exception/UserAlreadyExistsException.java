package com.Order.order_management.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String meassage){
        super(meassage);
    }
}
