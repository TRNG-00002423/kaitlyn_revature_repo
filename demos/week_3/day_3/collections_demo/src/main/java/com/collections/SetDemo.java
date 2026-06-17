package com.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        Set<String> mySet = new TreeSet<>();
        mySet.add("Scarlett");
        mySet.add("Clementine");
        mySet.add("Socks");
        mySet.add("Meia Noite");
        mySet.add("Mitzy");
        mySet.add("Mitzy"); // duplicate

        // HashSets don't maintain insertion order.
        System.out.println(mySet);
    }
}
