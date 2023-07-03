package com.example.demo.pattern.creation.abstraction;

/*
Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.
Note: family of related object

https://refactoring.guru/design-patterns/abstract-factory
 */
public class AbstractFactoryDemo {
    /**
     Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.
     */
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }
        app = new Application(factory);
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
