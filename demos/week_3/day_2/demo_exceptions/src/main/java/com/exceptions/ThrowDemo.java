package com.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThrowDemo {
    public static void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Access Denied - You must be 18 years or older to log in.");
        }
    }

    // This method is allowed to throw a FileNotFoundException.
    public static void readFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("abc.txt");
    }

    public static void main(String[] args) {
        try {
            checkAge(10);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
