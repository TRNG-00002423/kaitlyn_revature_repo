package com.rev.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rev.fileio.Student;

public class StreamsDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Scarlett", "Clementine", "Meia Noite", "Socks", "Mitzy");
        List<String> namesM = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("M")) {
                namesM.add(name);
            }
        }
        List<String> result = names.stream()
                .filter(name -> name.startsWith("M"))
                .collect(Collectors.toList());
        System.out.println(result.toString());

        List<String> upperName = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperName.toString());

        List<String> sorted = names.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted.toString());

        names.stream().forEach(System.out::println);

        List<String> upperC = names.stream()
                .filter(name -> name.startsWith("C"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperC.toString());

        Student student = null;
        if (student != null) {
            student.someMethod();
        } else {
            System.out.println("some code");
        }

    }
}
