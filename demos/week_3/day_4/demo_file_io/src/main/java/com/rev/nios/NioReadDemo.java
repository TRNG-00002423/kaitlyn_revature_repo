package com.rev.nios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioReadDemo {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("output.txt");
        String content = Files.readString(path);
        System.out.println("Data from file: " + content);
    }
}
