package com.multithreading.udemy.concurrentcollection.copyonwritearray;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteExample {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        for (String s : list) {
            list.add("D"); // No ConcurrentModificationException
            System.out.println(s);
        }
        System.out.println(list);
    }
}
