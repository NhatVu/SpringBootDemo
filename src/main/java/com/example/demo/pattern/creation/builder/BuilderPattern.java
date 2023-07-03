package com.example.demo.pattern.creation.builder;

/**
 * Builder is a creational design pattern that lets you construct complex objects step by step.
 * The pattern allows you to produce different types and representations of an object using the same construction code.
 */
public class BuilderPattern {
    public static void main(String[] args) {
        //Using builder to get the object in a single line of code and
        //without any inconsistent state or arguments management issues
        Computer comp = new Computer.ComputerBuilder(
                "500 GB", "2 GB").isBluetoothEnabled(true)
                .build();

        System.out.println(comp.toString());
    }
}
