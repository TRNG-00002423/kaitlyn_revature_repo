package com.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SortingDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("Scarlett");
        names.add("Clementine");
        names.add("Meia Noite");
        names.add("Socks");
        names.add("Mitzy");
        // A list is an ordered collection. Names are printed in the order they are
        // inserted.
        System.out.println(names);

        Collections.sort(names);
        System.out.println(names);

        Collections.sort(names, Collections.reverseOrder());
        System.out.println(names);
    }
}
