package com.revature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.PaymentException;
import com.revature.Product;

@ExtendWith(MockitoExtension.class)
public class StubTests {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private OrderService orderService;

    @Test
    void calculateTotal_multipleProducts_returnsCorrectSum() {
        // Arrange: Stub product lookups
        Product laptop = new Product("LAPTOP", "MacBook Pro", new BigDecimal("1999.99"));
        Product mouse = new Product("MOUSE", "Magic Mouse", new BigDecimal("79.99"));

        when(productRepository.findById("LAPTOP")).thenReturn(Optional.of(laptop));
        when(productRepository.findById("MOUSE")).thenReturn(Optional.of(mouse));

        // Act
        BigDecimal total = orderService.calculateTotal(List.of("LAPTOP", "MOUSE"));

        // Assert
        assertEquals(new BigDecimal("2079.98"), total);
    }

    @Test
    void calculateTotal_oneProduct_returnsCorrectSum() {
        Product cards = new Product("CARDS", "Deck of cards", new BigDecimal("7.99"));

        when(productRepository.findById("CARDS")).thenReturn(Optional.of(cards));

        BigDecimal total = orderService.calculateTotal(List.of("CARDS"));
        assertEquals(new BigDecimal("7.99"), total);
    }

    @Test
    void calculateTotal_nonExistentProduct_returnsZero() {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        BigDecimal total = orderService.calculateTotal(List.of("BOOK", "PHONE"));
        assertEquals(new BigDecimal("0.00"), total);
    }

    @Test
    void processPayment_gatewayTimeout_throwsPaymentException() {
        // Arrange: Payment gateway times out
        when(paymentGateway.charge(any(), any()))
                .thenThrow(new PaymentTimeoutException("Gateway timeout"));

        Order order = new Order();
        order.addLine("TEST", 1);
        PaymentDetails paymentDetails = new PaymentDetails(new BigDecimal(0));
        // Act & Assert
        assertThrows(PaymentException.class, () -> {
            orderService.processPayment(order, paymentDetails);
        });
    }
}
