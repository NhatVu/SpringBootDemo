package com.example.demo.pattern.structural.compositev2;

public class Car implements Product{
    private int price;

    public Car(int price){
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
