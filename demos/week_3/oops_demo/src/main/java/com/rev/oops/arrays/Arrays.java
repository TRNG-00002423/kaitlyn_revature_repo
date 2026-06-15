package com.rev.oops.arrays;

public class Arrays {
    public static void main(String[] args) {
        // Arrays have a constant size.
        int[] myArray = new int[5];
        String[] courses = {
                "Java", "Databases",
                "Operating Systems"
        };
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
        for (String course : courses) {
            System.out.println(course);
        }
    }

    String[] names = { "Curtis", "Juan", "Audy" };
    int[] ages = { 24, 22, 23 };
}
