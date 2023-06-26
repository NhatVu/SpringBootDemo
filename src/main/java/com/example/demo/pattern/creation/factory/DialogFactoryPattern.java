package com.example.demo.pattern.creation.factory;

/*
Common inteface for all buttons
 */
public abstract class DialogFactoryPattern {
    protected abstract Button createButton();

    public void render(){
        // --- other code ---
        Button okButton = createButton();
        okButton.render();
    }
}
