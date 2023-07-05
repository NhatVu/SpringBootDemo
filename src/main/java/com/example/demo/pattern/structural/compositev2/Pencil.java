package com.example.demo.pattern.structural.compositev2;

public class Pencil implements Product{
    private int price;

    public Pencil(int price){
        this.price = price;
    }


    @Override
    public int getPrice() {
        return this.price;
    }
}
