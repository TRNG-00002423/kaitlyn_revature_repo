package com.lambda;

public class MainClass {
    public static void main(String[] args) {
        AdditionImpl additionImpl1 = new AdditionImpl();
        System.out.println(additionImpl1.add(2, 3));

        // lambda function
        // saves us the intermediate step of writing the AdditionImpl class
        Addition sum = (a, b) -> a + b;

        int result = sum.add(5, 10);
        System.out.println(result);

        // a lambda function has 3 parts
        // 1. parameter
        // 2. arrow
        // 3. body
    }
}
