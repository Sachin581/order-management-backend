package com.Order.order_management.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String meassage){
        super(meassage);
    }
}
