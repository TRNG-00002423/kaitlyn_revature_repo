package com.revature.methods;

import java.util.Scanner;
import java.util.*; // import every method in java.util

public class DemoMethodsAgain {
    public static void main(String[] args) {
        System.out.println("Sum = " + sum(2, 3));
        System.out.println("Sum = " + sum(2, 3, 4));

        // constructor has not been explicitly defined yet
        // Constructors don't return anything - not even void.
        DemoMethodsAgain demoMethods = new DemoMethodsAgain();
    }

    public static int sum(int num1, int num2) {
        return num1 + num2;
    }

    // override
    public static int sum(int num1, int num2, int num3) {
        return num1 + num2 + num3;
    }

    public static int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

}
