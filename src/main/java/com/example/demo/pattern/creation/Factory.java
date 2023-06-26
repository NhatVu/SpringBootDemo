package com.example.demo.pattern.creation;

import com.example.demo.pattern.entity.HtmlDialog;
import com.example.demo.pattern.entity.WindowsDialog;

/*
Ref: https://refactoring.guru/design-patterns/factory-method
 */
public class Factory {
    private static DialogFactoryPattern dialogFactoryPattern;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * The concrete factory is usually chosen depending on configuration or
     * environment options.
     */
    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialogFactoryPattern = new WindowsDialog();
        } else {
            dialogFactoryPattern = new HtmlDialog();
        }
    }

    /**
     * All of the client code should work with factories and products through
     * abstract interfaces. This way it does not care which factory it works
     * with and what kind of product it returns.
     */
    static void runBusinessLogic() {
        dialogFactoryPattern.render();
    }
}
