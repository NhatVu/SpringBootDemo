package com.example.demo.pattern.creation.factory;

public class WindowsButton implements Button{
    @Override
    public void render() {
        System.out.println("Windows Button: Test");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World! Windows Button'");
    }
}
