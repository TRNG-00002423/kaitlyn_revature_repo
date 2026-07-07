package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit6 Assertions ComprehensiveDemo")
public class demoAssertionsComprehensive {

    @Test
    @DisplayName("assertEquals - Comparing values")
    void demonstrateEquals() {
        // Primitive comparison
        assertEquals(4, 2 + 2, "basic math should work");

        // String comparison
        String expected = "Hello World";
        String actual = "Hello " + "World";
        assertEquals(expected, actual);

        // Object comparison
        Integer num1 = Integer.valueOf(100);
        Integer num2 = Integer.valueOf(100);
        assertEquals(num1, num2);
    }

    @Test
    @DisplayName("assertEquals with delta - Floating point comparison")
    void demonstrateFloatingPointComparison() {
        double result = 0.1 + 0.2;

        // bad: this could fail due to floating point precision
        // assertEquals(0.3, result);

        assertEquals(0.3, result, 0.0001, "should be approximately 0.3");

        // PI comparison
        assertEquals(3.14159, Math.PI, 0.00001);
    }

    @Test
    @DisplayName("assertNotEquals - values should differ")
    void demonstrateNotEquals() {
        assertNotEquals("Hello", "World");
        assertNotEquals(null, "something");
        assertNotEquals(1, 2);
    }

    @Test
    @DisplayName("assertTrue/assertFalse - boolean conditions")
    void demonstrateBooleanAssertions() {
        // assertTrue
        assertTrue(5 > 3, "5 should be greater than 3");
        assertTrue("Hello".startsWith("H"));
        assertTrue(List.of(1, 2, 3).contains(2));

        // assertFalse
        assertFalse(5 < 3, "5 should not be less than 3");
        assertFalse("Hello".isEmpty());
        assertFalse(List.of(1, 2, 3).contains(99));
    }

    @Test
    @DisplayName("Why specific assertions beat assertTrue")
    void demonstrateSpecificVsGeneric() {
        int result = 7;
        // less informative: expected <true>, but was <false>
        // assertTrue(result == 5);
        // more informative
        assertEquals(5, result, "specific assertions give better error messages");
    }

    @Test
    @DisplayName("assertNull/assertNotNull")
    void demonstrateNullAssertions() {
        String nullValue = null;
        String nonNullValue = "exists";
        assertNull(nullValue, "should be null");
        assertNotNull(nonNullValue, "should not be null");
    }

    @Test
    @DisplayName("assertSame/assertNotSame - reference comparisons")
    void demonstrateReferenceAssertions() {
        String str1 = "something";
        String str2 = str1;
        String str3 = new String("something");

        assertSame(str1, str2);

        assertEquals(str1, str3);
        assertNotSame(str1, str3);
    }
}
