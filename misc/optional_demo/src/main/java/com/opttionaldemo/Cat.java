package com.opttionaldemo;

public class Cat {
    private String name;
    private int age;
    private String breed;

    public Cat(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public String meow() {
        return this.name + " says meow!";
    }

    public int humanYears() {
        if (this.age <= 0) {
            return 0;
        }
        switch (this.age) {
            case 1:
                return 15;
            case 2:
                return 25;
            default:
                return 25 * (4 * this.age);
        }
    }
}