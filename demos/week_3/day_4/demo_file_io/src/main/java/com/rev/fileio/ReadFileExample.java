package com.rev.fileio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFileExample {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("example.txt")) {
            int ch;
            try (FileWriter fw = new FileWriter("example_output.txt")) {
                while ((ch = fis.read()) != -1) {
                    System.out.print((char) ch);
                    fw.write((char) ch);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}