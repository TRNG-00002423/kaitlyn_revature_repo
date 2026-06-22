package com.opttionaldemo;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());
        String name = "Bing Bong";
        Optional<String> bingBong = Optional.of(name);
    }
}
