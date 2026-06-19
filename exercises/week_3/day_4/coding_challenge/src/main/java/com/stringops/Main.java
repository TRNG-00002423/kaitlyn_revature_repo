package com.stringops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<String> getSmallestPrefixes(List<String> list) {

        List<String> prefixes = new ArrayList<>();
        for (String word : list) {
            if (word.length() == 1) {
                prefixes.add(word);
                continue;
            }
            int lastIndex = 0;
            boolean checked = false;
            while (!checked) {
                String substr = word.substring(0, lastIndex);
                final String prefix = substr;
                List<String> samePrefixList = list.stream()
                        .filter(s -> s.startsWith(prefix))
                        .collect(Collectors.toList());
                if (samePrefixList.size() == 1) {
                    checked = true;
                    prefixes.add(substr);
                } else if (samePrefixList.stream().filter(s -> s.equals(substr)).collect(Collectors.toList())
                        .size() == samePrefixList.size()) {
                    checked = true;
                    prefixes.add(substr);
                }
                lastIndex++;
            }
        }
        return prefixes;
    }

    public static void main(String[] args) {
        List<String> sample = new ArrayList<>(
                Arrays.asList(new String[] { "dog", "cat", "apple", "apricot", "fish" }));

        System.out.println(getSmallestPrefixes(sample).toString());

        List<String> test1 = new ArrayList<>(
                Arrays.asList(new String[] { "zebra", "dog", "duck", "dove" }));
        List<String> test2 = new ArrayList<>(
                Arrays.asList(new String[] { "a", "b", "c" }));
        List<String> test3 = new ArrayList<>(
                Arrays.asList(new String[] { "apple", "apple", "ape", "apricot" }));
        List<String> test4 = new ArrayList<>(
                Arrays.asList(new String[] { "a", "apple", "apple", "ape", "apricot" }));
        List<String> test5 = new ArrayList<>(
                Arrays.asList(new String[] { "aa", "ab", "ac" }));
        List<String> test6 = new ArrayList<>(
                Arrays.asList(new String[] { "hello", "hello" }));

        System.out.println(getSmallestPrefixes(test1).toString());
        System.out.println(getSmallestPrefixes(test2).toString());
        System.out.println(getSmallestPrefixes(test3).toString());
        System.out.println(getSmallestPrefixes(test4).toString());
        System.out.println(getSmallestPrefixes(test5).toString());
        System.out.println(getSmallestPrefixes(test6).toString());
    }
}