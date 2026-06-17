package com.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class StudentManager {
    public static void main(String[] args) {
        Student s1 = new Student(105, "Andrew");
        Student s2 = new Student(102, "Jasdhir");
        Student s3 = new Student(103, "Benson");

        List<Student> students = new ArrayList<Student>();

        students.add(s1);
        students.add(s2);
        students.add(s3);

        Collections.sort(students);
        System.out.println(students);

        students.sort(new StudentNameComparator());
        System.out.println(students);
    }
}
