package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Lifecycle Demo")
public class demoLifecycleMethods {
    private static String sharedResource;
    private static int testCounter;

    private Calculator calculator;
    private StringBuilder testLog;

    @BeforeAll
    static void setupClass() {
        System.out.println("@BeforeAll: Setting up test class ONCE");

        // simulate expensive setup
        sharedResource = "database connection";
        testCounter = 0;

        // you might:
        // - start a mock server
        // - open a database connection
        // - load a large test data file
        // - initialize heavy resources
    }

    @BeforeEach
    void setUp() {
        testCounter++;
        System.out.println("@BeforeEach: Preparing test #" + testCounter);
        calculator = new Calculator();
        testLog = new StringBuilder();
        testLog.append("Test started");

        // This is where you would:
        // - create fresh object instances
        // - reset mocks
        // - prepare test-specific data
        // - start a transaction
    }

    @Test
    @DisplayName("First test - calculator is fresh")
    void testOne() {
        System.out.println("running test one");
        testLog.append("Test one executed");
        assertEquals(5, calculator.add(2, 3));
        assertNotNull(sharedResource);
    }

    @Test
    @DisplayName("Second test - calculator is fresh again")
    void testTwo() {
        System.out.println("running test two");
        testLog.append("Test two executed");
        // even if testOne modified the calculator, testTwo gets a fresh one
        assertEquals(8, calculator.add(5, 3));
    }
}