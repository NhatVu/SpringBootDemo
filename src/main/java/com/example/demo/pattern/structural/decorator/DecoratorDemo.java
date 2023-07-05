package com.example.demo.pattern.structural.decorator;

import java.io.File;

/*
Decorator is a structural design pattern that lets you attach new behaviors to objects
by placing these objects inside special wrapper objects that contain the behaviors.
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
        System.out.println("absolute file path: " + new File("out/OutputDemo.txt").getAbsoluteFile());
    }
}
