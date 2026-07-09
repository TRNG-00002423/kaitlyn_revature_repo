package com.revature.exceptions;

public class InvalidOrderStateException extends RuntimeException {
    InvalidOrderStateException(String message) {
        super(message);
    }
}
