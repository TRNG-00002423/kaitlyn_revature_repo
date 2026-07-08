package com.revature;

public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}