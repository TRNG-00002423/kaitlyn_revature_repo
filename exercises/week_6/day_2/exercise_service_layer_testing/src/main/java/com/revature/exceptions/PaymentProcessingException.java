package com.revature.exceptions;

public class PaymentProcessingException extends RuntimeException {
    PaymentProcessingException(String message) {
        super(message);
    }

    public PaymentProcessingException(String message, Exception lastException) {
        super(message);
    }
}
