package com.example.demo.pattern.structural.composite;

/*
Composite is a structural design pattern that lets you compose objects into tree structures
 and then work with these structures as if they were individual objects.

 A Decorator is like a Composite but only has one child component. There’s another significant difference:
 Decorator adds additional responsibilities to the wrapped object, while Composite just “sums up” its children’s results.
 https://www.geeksforgeeks.org/composite-design-pattern/
 */
public class CompositeDemo {
    public static void main (String[] args)
    {
        Developer dev1 = new Developer(100, "Lokesh Sharma", "Pro Developer");
        Developer dev2 = new Developer(101, "Vinay Sharma", "Developer");
        CompanyDirectory engDirectory = new CompanyDirectory();
        engDirectory.addEmployee(dev1);
        engDirectory.addEmployee(dev2);

        Manager man1 = new Manager(200, "Kushagra Garg", "SEO Manager");
        Manager man2 = new Manager(201, "Vikram Sharma ", "Kushagra's Manager");

        CompanyDirectory accDirectory = new CompanyDirectory();
        accDirectory.addEmployee(man1);
        accDirectory.addEmployee(man2);

        CompanyDirectory directory = new CompanyDirectory();
        directory.addEmployee(engDirectory);
        directory.addEmployee(accDirectory);
        directory.showEmployeeDetails();
    }
}
