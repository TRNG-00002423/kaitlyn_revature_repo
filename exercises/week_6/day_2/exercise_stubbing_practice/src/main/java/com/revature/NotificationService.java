package com.revature;

public interface NotificationService {
    void sendOrderConfirmation(Order order); // void

    void sendShippingUpdate(Order order, String status); // void
}