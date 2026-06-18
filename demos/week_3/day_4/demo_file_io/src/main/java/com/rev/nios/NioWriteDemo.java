package com.rev.nios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioWriteDemo {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("output.txt");
        Files.writeString(path, "Hello NIO!");
        System.out.println("Data written to the file.");
    }
}
