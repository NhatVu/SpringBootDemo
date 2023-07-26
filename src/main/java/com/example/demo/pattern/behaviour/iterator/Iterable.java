package com.example.demo.pattern.behaviour.iterator;

public interface Iterable {
    Iterator createEvenIterator();
    Iterator createOddIterator();
}
