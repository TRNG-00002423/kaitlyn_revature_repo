package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class parametrizedAndExceptionTests {

    Calculator calculator = new Calculator();

    @Nested
    @DisplayName("@ValueSource")
    class ValueSourceExample {
        @ParameterizedTest
        @ValueSource(ints = { 2, 4, 6, 8, -2, 100 })
        @DisplayName("Even numbers should be identified correctly")
        void isEven_evenNumbers_returnsTrue(int num) {
            assertTrue(calculator.isEven(num), num + " should be even");
        }

        @ParameterizedTest
        @ValueSource(strings = { "Hello", "world", "Junit6", "testing" })
        @DisplayName("Strings can be parametrized too")
        void stringLength_variousStrings_calculated(String s) {
            assertTrue(s.length() > 0);
        }
    }

    @Nested
    @DisplayName("Null and Empty Source Examples")
    class NullEmptyExamples {
        @ParameterizedTest
        @NullSource
        @DisplayName("Null Input Handling")
        void handleNull_nullInput_handled(String input) {
            assertNull(input);
        }

        @ParameterizedTest
        @EmptySource
        @DisplayName("Empty Input Handled")
        void handleEmpty_emptyInput_handled(String input) {
            assertTrue(input.isEmpty());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = { " ", "\t", "\n" })
        @DisplayName("Blank strings should be rejected")
        void validateInput_blankStrings_rejected(String input) {
            assertTrue(input == null || input.trim().isEmpty());
        }

    }

    @Nested
    @DisplayName("@CsvSource examples")
    class CsvSourceExamples {
        @ParameterizedTest
        @CsvSource({
                "1, 2, 3",
                "0, 0, 0",
                "-1, 1, 0",
                "100, 200, 300",
                "-5, -10, -15"
        })
        @DisplayName("Addition with various inputs")
        void add_variousInputs_correctResult(int a, int b, int expected) {
            assertEquals(expected, calculator.add(a, b));
        }

        @ParameterizedTest(name = "{0}+{1}={2}")
        @CsvSource({
                "1, 2, 3",
                "2, 3, 5",
                "10, 20, 30"
        })
        @DisplayName("Addition with custom display name")
        void add_withCustomDisplayName(int a, int b, int expected) {
            assertEquals(expected, calculator.add(a, b));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "hello | 5",
                "world | 5",
                "Junit | 5",
                "testing | 7"
        }, delimiter = '|')
        @DisplayName("String length with custom delimiter")
        void stringLength_customDelimiter(String input, int expectedLength) {
            assertEquals(expectedLength, input.length());
        }
    }

    @Nested
    @DisplayName("@MethodSource examples")
    class MethodSourceExamples {

        @ParameterizedTest
        @MethodSource("provideNumbersForAbsoluteValue")
        @DisplayName("Absolute value calculation")
        void absoluteValue_variousNumbers_correctResult(int input, int expected) {
            assertEquals(expected, calculator.absoluteValue(input));
        }

        static Stream<Arguments> provideNumbersForAbsoluteValue() {
            return Stream.of(
                    Arguments.of(5, 5),
                    Arguments.of(-5, 5),
                    Arguments.of(0, 0),
                    Arguments.of(Integer.MIN_VALUE + 1, Integer.MAX_VALUE));
        }

        @ParameterizedTest
        @MethodSource("provideMinMaxTestCases")
        @DisplayName("Min/Max operations")
        void minMax_variousCases_correctResult(int a, int b, int expectedMin, int expectedMax) {
            assertAll(
                    () -> assertEquals(expectedMin, calculator.min(a, b)),
                    () -> assertEquals(expectedMax, calculator.max(a, b)));
        }

        static Stream<Arguments> provideMinMaxTestCases() {
            return Stream.of(
                    Arguments.of(1, 5, 1, 5),
                    Arguments.of(5, 1, 1, 5),
                    Arguments.of(0, 0, 0, 0),
                    Arguments.of(-5, 5, -5, 5),
                    Arguments.of(-10, -5, -10, -5));
        }
    }

    enum Operation {
        ADD, SUBTRACT, MULTIPLY
    }

    @Nested
    class EnumSourceExamples {
        @EnumSource(Operation.class)
        @DisplayName("All operations should be valid")
        void operation_allValues_valid(Operation op) {
            assertNotNull(op);
            assertNotNull(op.name());
        }
    }

    @Nested
    @DisplayName("Exception Testing Demo")
    class ExceptionTestingExamples {
        // always verify exception type
        @Test
        @DisplayName("Division by zero throws ArithmeticException")
        void divide_byZero_throwsArithmeticException() {
            assertThrows(ArithmeticException.class, () -> {
                calculator.divide(4, 0);
            });
        }

        @Test
        @DisplayName("Capture exception and verify message")
        void divide_byZero_exceptionHasCorrectMessage() {
            ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
                calculator.divide(10, 0);
            });
            assertEquals("cannot divide by zero", exception.getMessage().toLowerCase());
        }

        @Test
        @DisplayName("Valid division does not throw exception")
        void divide_validInputs_noException() {
            assertDoesNotThrow(() -> {
                calculator.divide(10, 2);
            });
        }
    }

}
