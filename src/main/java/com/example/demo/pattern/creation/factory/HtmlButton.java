package com.example.demo.pattern.creation.factory;

public class HtmlButton implements Button{

    public HtmlButton(){

    }
    public HtmlButton(HtmlButton o){
        Integer a = 1;

    }
    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
