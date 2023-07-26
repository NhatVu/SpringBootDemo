package com.example.demo.pattern.behaviour.iterator;

/**
 * Iterator is a behavioral design pattern that lets you traverse elements of a collection
 * without exposing its underlying representation (list, stack, tree, etc.).
 * https://refactoring.guru/design-patterns/iterator
 */
public class IteratorDemo {
    public static void main(String[] args) {
        StrangeList strangeList = new StrangeList();
        Iterator odd = strangeList.createOddIterator();
        System.out.println("Odd iterator");
        while(odd.hasNext()){
            System.out.println(odd.getNext());
        }

        Iterator even = strangeList.createEvenIterator();
        System.out.println("Even iterator");
        while(even.hasNext()){
            System.out.println(even.getNext());
        }
    }
}
