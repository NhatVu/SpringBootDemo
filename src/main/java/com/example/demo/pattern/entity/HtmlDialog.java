package com.example.demo.pattern.entity;

import com.example.demo.pattern.creation.DialogFactoryPattern;

public class HtmlDialog extends DialogFactoryPattern {
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
