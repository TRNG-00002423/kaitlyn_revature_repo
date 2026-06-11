package com.revature.demo;

public class StringDemo {

    public static void main(String[] args) {
        String s1 = "Hello"; // Stored in the string pool
        String s2 = new String("Hello"); // stored in the heap
        String s3 = s1.concat(" World"); // When changing a string, it must be reassigned.

        System.out.println(s3);
        System.out.println(s1.equalsIgnoreCase(s2));

        s1 = s1.toUpperCase();
        String s4 = " This string has leading and trailing spaces. ";
        System.out.println(s4.trim());

        System.out.println(s1.replace("E", "A"));

        StringBuffer sbuffer = new StringBuffer();
        sbuffer.insert(0, "abcd");
        System.out.println(sbuffer);
        sbuffer.replace(0, 0, "skfagbd");
        System.out.println(sbuffer);
    }
}
