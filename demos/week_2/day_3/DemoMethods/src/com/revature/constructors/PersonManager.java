package com.revature.constructors;

public class PersonManager {
    public static void main(String[] args) {
        Person person = new Person("Jasdhir", 46);
        System.out.println(person.displayPerson());

        // The Person() constructor is overloaded.
        Person oscar = new Person();
        oscar.setName("Oscar");
        oscar.setAge(28);
        System.out.println(oscar.displayPerson());
    }
}
