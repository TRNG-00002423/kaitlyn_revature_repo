package com.collections;

import java.util.Map;
import java.util.HashMap;

public class MapDemo {
    // Maps aren't Iterable and don't have the same methods that other collection
    // classes have.

    public static void main(String[] args) {
        Map<String, Double> scores = new HashMap<String, Double>();
        scores.put("Scarlett", 90.2);
        scores.put("Clementine", 91.4);

        System.out.println(scores);

        // How do you iterate through a map?
        // Map is not Iterable, but map.entrySet is.
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
