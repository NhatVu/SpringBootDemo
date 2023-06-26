package com.example.demo.pattern.entity;

import com.example.demo.pattern.creation.DialogFactoryPattern;

public class WindowsDialog extends DialogFactoryPattern {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
