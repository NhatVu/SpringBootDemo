package com.example.demo.pattern.structural.adapter;

public class SquarePegAdapter extends RoundPeg{
    private SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    public double getRadius(){
        // The adapter pretends that it's a round peg with a
        // radius that could fit the square peg that the adapter
        // actually wraps.
        return squarePeg.getWidth() * Math.sqrt(2) / 2;
    }
}
