package com.revature;

import java.math.BigDecimal;

/**
 * PaymentDetails
 */
public class PaymentDetails {

    private BigDecimal balance;

    PaymentDetails(BigDecimal balance) {
        this.balance = balance;
    }

    void addFunds(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    void removeFunds(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new PaymentException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
    }
}
