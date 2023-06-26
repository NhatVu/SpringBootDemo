package com.example.demo.pattern.creation.factory;

/*
Ref: https://refactoring.guru/design-patterns/factory-method

Factory Method is a creational design pattern that provides an interface for creating objects in a superclass,
but allows subclasses to alter the type of objects that will be created.
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
