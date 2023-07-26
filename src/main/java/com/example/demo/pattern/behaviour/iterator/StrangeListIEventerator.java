package com.example.demo.pattern.behaviour.iterator;

import java.util.NoSuchElementException;

public class StrangeListIEventerator implements Iterator{

    private StrangeList strangeList;
    private int size;
    private int currentIndex = 0;
    public StrangeListIEventerator(StrangeList strangeList){
        this.strangeList = strangeList;
        int n = strangeList.size();
        if (n % 2 == 0){
            size = n - 1;
        }else {
            size = n;
        }
    }
    // 0  1  2  3
    @Override
    public boolean hasNext() {
        return currentIndex < size && currentIndex != size;
    }

    @Override
    public int getNext() {
        if (currentIndex > size){
            throw new NoSuchElementException();
        }
        int value = strangeList.get(currentIndex);
        currentIndex += 2;
        return value;
    }

}
