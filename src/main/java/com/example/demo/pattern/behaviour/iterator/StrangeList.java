package com.example.demo.pattern.behaviour.iterator;

import java.util.ArrayList;
import java.util.List;

public class StrangeList implements Iterable{
    private List<Integer> internal = new ArrayList<>();

    public StrangeList(){
        internal.add(0);
        internal.add(1);
        internal.add(2);
        internal.add(3);

        internal.iterator();
    }

    public int size(){
        return internal.size();
    }

    public int get(int index){
        return internal.get(index);
    }

    @Override
    public Iterator createEvenIterator() {
        return new StrangeListIEventerator(this);
    }

    @Override
    public Iterator createOddIterator() {
        return new StrangeListIOddIIterator(this);
    }
}
