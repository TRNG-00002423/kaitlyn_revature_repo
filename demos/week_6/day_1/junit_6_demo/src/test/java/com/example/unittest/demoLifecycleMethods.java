package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

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

    @Test
    @DisplayName("Third test - demonstrates isolation")
    void testThree() {
        System.out.println("running test three");
        testLog.append("Test three executed");

        assertEquals(18, calculator.multiply(9, 2));

        assertTrue(testLog.toString().contains("Test three"));
        assertFalse(testLog.toString().contains("Test two"));
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Cleaning up after test: " + testInfo.getDisplayName());

        testLog.append("Test completed");
        System.out.println("Log: " + testLog.toString());

        calculator = null;
        testLog = null;

        // roll back transactions
        // delete test data
        // close file handlers
        // reset any modified state
    }

    @AfterAll
    static void tearDownClass() {
        System.out.println("@AfterAll: cleaning up test class ONCE");
        System.out.println("Total tests run: " + testCounter);

        // release shared resources
        sharedResource = null;

        // stop mock servers
        // close database connections
        // clean up temp files
        // release expensive resources
    }
}