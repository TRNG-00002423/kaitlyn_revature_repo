package com.rev.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedDemo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"));
                // this makes a new file called example_buffer.txt if it doesn't already exist
                BufferedWriter writer = new BufferedWriter(new FileWriter("example_buffer.txt"));) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine(); // The BufferedWriter doesn't automatically make new lines.
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
