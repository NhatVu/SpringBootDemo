package com.example.demo.pattern.creation.factory;

public class WindowsDialog extends DialogFactoryPattern {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
