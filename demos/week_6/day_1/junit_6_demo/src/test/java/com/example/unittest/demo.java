package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Calculator Basic Tests - JUnit6 Fundamentals")
class demo {
    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Adding 2 positive numbers returns their sum")
    void addTwoPositiveNumbersReturnsSum() {
        // Arrange - set up test data
        int a = 5;
        int b = 3;

        // Act - Execute the method under
        int result = calculator.add(a, b);
        // Assert - Verify the outcome
        assertEquals(result, 8);
    }

    @Test
    @DisplayName("Subtracting returns the difference")
    void subtractTwoNumbersReturnsDifference() {
        // Simple one-liner when the logic is straightforward
        assertEquals(7, calculator.subtract(10, 3));
    }

    @Test
    @DisplayName("Adding zero doesn't change the number")
    void addingZeroReturnsOriginalNumber() {
        assertEquals(42, calculator.add(42, 0), "adding zero should return original");
        assertEquals(42, calculator.add(0, 42), "Zero plus number should return number");
    }

    @Test
    @DisplayName("Multiplying by zero returns zero")
    void multiplyingByZeroReturnsZero() {
        assertEquals(0, calculator.multiply(100, 0));
        assertEquals(0, calculator.multiply(0, 100));
    }

    @Test
    @DisplayName("Adding negative numbers is handled correctly")
    void addingNegatives() {
        assertEquals(-8, calculator.add(-5, -3), "Two negatives");
        assertEquals(2, calculator.add(5, -3), "Positive and negative");
        assertEquals(-2, calculator.add(-5, 3), "Negative and positive");
    }

    @Test
    @DisplayName("Even numbers identified correctly")
    void isEvenVariousNumbersIdentifiedCorrectly() {
        assertTrue(calculator.isEven(2));
        assertTrue(calculator.isEven(0));
        assertTrue(calculator.isEven(-4));

        assertFalse(calculator.isEven(7));
        assertFalse(calculator.isEven(1));
        assertFalse(calculator.isEven(-3));
    }

    @Test
    @DisplayName("Positive Number Detection Works Correctly")
    void positiveNumberDetectionWorks() {
        assertTrue(calculator.isPositive(1), "One is positive");
        assertTrue(calculator.isPositive(100), "100 is positive");
        assertFalse(calculator.isPositive(0), "Zero is not positive");
        assertFalse(calculator.isPositive(-1), "-1 is not positive");
    }

    @Test
    @DisplayName("Max works correctly")
    void maxWorksCorrectly() {
        assertEquals(7, calculator.max(7, 2));
    }

    // other common naming conventions


}