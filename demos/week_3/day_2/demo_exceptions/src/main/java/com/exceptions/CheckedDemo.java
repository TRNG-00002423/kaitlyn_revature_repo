package com.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedDemo {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("abc.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
