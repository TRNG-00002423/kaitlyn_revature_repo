package com.custom;

// If you want a custom runtime exception, extend the RuntimeException class instead.
public class InsufficientFundsException extends Exception {
    private double shortFall;

    public InsufficientFundsException(double shortFall) {
        super("Insufficient funds: need " + shortFall + " more"); // call the parent Exception constructor
    }
}
