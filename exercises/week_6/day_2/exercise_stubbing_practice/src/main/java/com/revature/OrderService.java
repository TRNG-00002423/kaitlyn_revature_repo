package com.revature;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private ProductRepository repository;
    private PaymentGateway gateway;

    OrderService(ProductRepository repository, PaymentGateway gateway) {
        this.repository = repository;
        this.gateway = gateway;
    }

    BigDecimal calculateTotal(List<String> of) {
        BigDecimal total = new BigDecimal("0.00");
        for (String productName : of) {
            Optional<Product> product = this.repository.findById(productName);
            if (!product.isEmpty()) {
                total = total.add(product.get().getPrice());
            }
        }
        return total;
    }

    void processPayment(Order order, PaymentDetails paymentDetails) {
        BigDecimal orderTotal = calculateTotal(order.getProducts());
        gateway.charge(orderTotal, paymentDetails);
    }

}
