package com.example.demo.pattern.creation.factory;

public class HtmlDialog extends DialogFactoryPattern {
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
