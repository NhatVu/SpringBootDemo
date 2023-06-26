package com.example.demo.pattern.creation;

import com.example.demo.pattern.entity.Button;
import com.example.demo.pattern.entity.HtmlButton;
import com.example.demo.pattern.entity.WindowsButton;
import org.apache.commons.lang3.StringUtils;

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
