package com.revature.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GreeterTest {

    @Test
    public void greetsSteve() {
        Greeter greeter = new Greeter();
        assertEquals("Hello, Steve", greeter.hello("Steve"));
    }
}
