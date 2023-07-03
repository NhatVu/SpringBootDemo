package com.example.demo.pattern.structural.adapter;

/*
incompatible class SquarePeg,
and want to work with RoundHole, to check if squarePeg is fitted in hole
 */
public class SquarePeg {
    private double width;

    public SquarePeg(double width){
        this.width = width;
    }

    public double getWidth(){
        return this.width;
    }
}
