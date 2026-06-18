package com.rev.fileio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StudentWrite {
    public static void main(String[] args) {
        Student s = new Student(101, "ABC", 24);
        try (FileOutputStream fos = new FileOutputStream("student.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(s);
            System.out.println("Student written to student.dat");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
