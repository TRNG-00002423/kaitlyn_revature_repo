import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import com.example.week6.Calculator;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @ParameterizedTest
    @ValueSource(ints = { 2, 4, 6, 100, 0, -2 })
    void isEven_evenNumbers_returnsTrue(int number) {
        assertTrue(calculator.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 3, 5, 99, -1, -3 })
    void isEven_oddNumbers_returnsFalse(int number) {
        assertFalse(calculator.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 4, 3, 99, 100 })
    void isPositive_positiveNumbers_returnsTrue(int number) {
        assertTrue(calculator.isPositive(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, -2, -4, -3, -99, -100 })
    void isPositive_negativeNumbers_returnsFalse(int number) {
        assertFalse(calculator.isPositive(number));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "0, 0, 0",
            "-1, 1, 0",
            "100, 200, 300",
            "143, 0, 143",
            "-40, -40, -80"
    })
    void add_variousInputs_returnsCorrectSum(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1, 1",
            "5, 5, 0",
            "0, 143, -143",
            "300, 200, 100",
            "0, 0, 0",
            "1, -1, 2"
    })
    void subtract_variousInputs_returnsCorrectDifference(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "47, 5, 235",
            "47, 1, 47",
            "9, 0, 0",
            "300, -1, -300",
            "4, 4, 16",
            "5, -6, -30"
    })
    void multiply_variousInputs_returnsCorrectProduct(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "42, 6, 7",
            "90, 10, 9",
            "5, 2, 2",
            "100, 1, 100",
            "0, 1, 0",
            "8000, 4, 2000"
    })
    void divide_variousInputs_returnsCorrectQuotient(int a, int b, int expected) {
        assertEquals(expected, calculator.divide(a, b));
    }

    @ParameterizedTest
    @ValueSource(ints = { 6, -1, 1, 0, 9827 })
    void divide_byZero_throwsArithmeticException(int a) {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(a, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideDivisionTestCases")
    void divide_variousCases_returnsCorrectQuotient(int a, int b, int expected) {
        assertEquals(expected, calculator.divide(a, b));
    }

    static Stream<Arguments> provideDivisionTestCases() {
        return Stream.of(
                Arguments.of(10, 2, 5),
                Arguments.of(9, 3, 3),
                Arguments.of(-10, 2, -5),
                Arguments.of(7, 2, 3) // Integer division
        );
    }

    @ParameterizedTest
    @MethodSource("providePowerTestCases")
    void power_variousCases_returnsCorrectResult(int a, int b, int expected) {
        assertEquals(expected, calculator.power(a, b));
    }

    static Stream<Arguments> providePowerTestCases() {
        return Stream.of(
                Arguments.of(2, 2, 4),
                Arguments.of(2, 3, 8),
                Arguments.of(4, 7, 16384),
                Arguments.of(7, 4, 2401),
                Arguments.of(6, 0, 1),
                Arguments.of(0, 9078, 0));
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({ "1, 2, 3", "4, 5, 9" })
    void add_customDisplayName(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
    // Output: "1 + 2 = 3", "4 + 5 = 9"

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "  ", "\t", "\n" })
    void isBlank_blankInputs_returnsTrue(String input) {
        assertTrue(StringUtils.isBlank(input));
    }
}
