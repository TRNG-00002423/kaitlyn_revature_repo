package com.kaitlyn.qa.week2;

/**
 * Hello world!
 */
public class Greeter {
    public static String hello(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be blank or empty.");
        }
        return "Hello, " + name;
    }
}
