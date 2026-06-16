package com.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryWithDemo {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("abc.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader fileReader2 = new FileReader("xyz.txt")) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
