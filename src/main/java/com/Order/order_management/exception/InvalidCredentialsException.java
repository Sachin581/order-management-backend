package com.Order.order_management.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String meassage){
        super(meassage);
    }
}
