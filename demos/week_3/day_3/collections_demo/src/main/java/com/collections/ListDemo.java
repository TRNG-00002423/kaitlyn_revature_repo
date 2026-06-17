package com.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListDemo {
    public static void main(String[] args) {
        // A list is an ordered collection that allows duplicate elements.
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add(1, "Pineapple");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Blue");

        LinkedList<String> otherLinkedList = new LinkedList<>();
        otherLinkedList.add("Green");
        otherLinkedList.add("Yellow");
        otherLinkedList.addFirst("Pink");
        otherLinkedList.addLast("Brown");

        linkedList.addAll(otherLinkedList);
        for (String color : linkedList) {
            System.out.println(color);
        }
        System.out.println(linkedList.contains("Green"));
        System.out.println(linkedList.contains("Pink"));
        System.out.println(linkedList.size());
    }
}
