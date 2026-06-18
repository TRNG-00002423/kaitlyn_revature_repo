package com.rev.fileio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StudentRead {
    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("student.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Student s = (Student) ois.readObject();
            System.out.println(s.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // System.out.println(s.toString());
    }
}
