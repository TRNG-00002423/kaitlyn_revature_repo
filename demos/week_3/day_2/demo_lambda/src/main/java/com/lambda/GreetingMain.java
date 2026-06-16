package com.lambda;

public class GreetingMain {
    public static void main(String[] args) {
        // Greeting greet = () -> System.out.println("Hello");
        // greet.sayHello();

        Greeting greetWithName = name -> {
            String nameCaps = name.toUpperCase();
            return "Hello " + nameCaps;
        };

        System.out.println(greetWithName.sayHello("Kaitlyn"));
    }
}
