package com.exceptions;

public class UncheckedDemo {
    public static void main(String[] args) {

        int[] myArray = new int[5];
        // code that throws ArrayIndexOutOfBoundsException
        try {
            myArray[5] = 10;
            System.out.println(myArray[5]);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            e.printStackTrace();
        }

    }
}