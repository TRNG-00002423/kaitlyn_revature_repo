package com.revature.exceptions;

public class FraudDetectedException extends RuntimeException {
    FraudDetectedException(String message) {
        super(message);
    }
}
