import org.junit.jupiter.api.Test;

import com.example.week6.Calculator;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private static Calculator calculator = new Calculator();

    @Test
    @DisplayName("Two Positive Numbers")
    void add_twoPositiveNumbers_getResult() {
        assertEquals(8, calculator.add(5, 3), "5+3 should be 8");
    }

    @Test
    @DisplayName("Positive and Negative")
    void add_positiveAndNegative_getResult() {
        assertEquals(7, calculator.add(10, -3), "10 + (-3) should be 7");
    }

    @Test
    @DisplayName("Two Negative Numbers")
    void add_twoNegative_getResult() {
        assertEquals(-8, calculator.add(-5, -3), "-5 + (-3) should be -8");
    }

    @Test
    @DisplayName("Adding zero")
    void add_numAndZero_getNum() {
        assertEquals(42, calculator.add(0, 42), "0 + 42 should be 42");
    }

    @Test
    @DisplayName("Basic subtraction")
    void subtract_posPos_getResult() {
        assertEquals(7, calculator.subtract(10, 3), "10 - 3 should be 7");
    }

    @Test
    @DisplayName("Subtracting a larger number")
    void subtract_largerNumber_getNegative() {
        assertEquals(-10, calculator.subtract(10, 20), "10 - 20 should be -10");
    }

    @Test
    @DisplayName("Subtracting zero")
    void subtract_zero_getOriginal() {
        assertEquals(5, calculator.subtract(5, 0), "5 - 0 should be 5");
    }

    @Test
    @DisplayName("Checking even-ness of even positive numbers")
    void isEven_positiveEvenNumbers_getTrue() {
        assertTrue(calculator.isEven(2), "2 should be even");
        assertTrue(calculator.isEven(4), "4 should be even");
        assertTrue(calculator.isEven(100), "100 should be even");
    }

    @Test
    @DisplayName("Checking even-ness of odd positive numbers")
    void isEven_positiveOddNumbers_getFalse() {
        assertFalse(calculator.isEven(1), "1 should not be even");
        assertFalse(calculator.isEven(3), "3 should not be even");
        assertFalse(calculator.isEven(99), "99 should not be even");
    }

    @Test
    @DisplayName("Evenness of zero - should be even")
    void isEven_zero_getTrue() {
        assertTrue(calculator.isEven(0), "0 should be even");
    }

    @Test
    @DisplayName("isEven is consistent with negative even numbers")
    void isEven_negativeEvenNumbers_getTrue() {
        assertTrue(calculator.isEven(-2), "-2 should be even");
        assertTrue(calculator.isEven(-4), "-4 should be even");
        assertTrue(calculator.isEven(-100), "-100 should be even");
    }

    @Test
    @DisplayName("isEven is consistent with negative odd numbers")
    void isEven_negativeOddNumbers_getFalse() {
        assertFalse(calculator.isEven(-1), "-1 should not be even");
        assertFalse(calculator.isEven(-3), "-3 should not be even");
        assertFalse(calculator.isEven(-99), "-99 should not be even");
    }

    @Test
    @DisplayName("Positive numbers identified positive")
    void isPositive_positiveNumbers_getTrue() {
        assertTrue(calculator.isPositive(1), "1 should be positive");
        assertTrue(calculator.isPositive(100), "100 should be positive");
    }

    @Test
    @DisplayName("Negative numbers identified not-positive")
    void isPositive_negativeNumbers_getFalse() {
        assertFalse(calculator.isPositive(-1), "-1 should not be positive");
        assertFalse(calculator.isPositive(-100), "-100 should not be positive");
    }

    @Test
    @DisplayName("Zero not identified as positive")
    void isPositive_zero_getFalse() {
        assertFalse(calculator.isPositive(0), "0 should not be positive");
    }

}
