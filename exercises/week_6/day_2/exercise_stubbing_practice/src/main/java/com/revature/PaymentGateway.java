package com.revature;

import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentResult charge(BigDecimal amount, PaymentDetails details);

    void refund(String transactionId, BigDecimal amount); // void
}
