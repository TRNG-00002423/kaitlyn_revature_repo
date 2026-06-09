package com.kaitlyn.qa.week2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class GreeterTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void greeterGreetsName() {
        assertEquals("Hello, Steve", Greeter.hello("Steve"));
        assertEquals("Hello,  ", Greeter.hello(" "));
        assertEquals("Hello, First Last", Greeter.hello("First Last"));
    }

    @Test
    public void greeterHandlesNull() {
        assertThrows(IllegalArgumentException.class, () -> Greeter.hello(null));
        assertThrows(IllegalArgumentException.class, () -> Greeter.hello(""));
    }
}
