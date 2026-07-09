package com.revature.exceptions;

public class PaymentTimeoutException extends RuntimeException {
    PaymentTimeoutException(String message) {
        super(message);
    }
}
