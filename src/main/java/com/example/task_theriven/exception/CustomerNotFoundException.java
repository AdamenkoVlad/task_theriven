package com.example.task_theriven.exception;

import com.example.task_theriven.model.Customer;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
