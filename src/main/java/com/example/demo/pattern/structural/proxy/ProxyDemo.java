package com.example.demo.pattern.structural.proxy;

/*
Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object.
A proxy controls access to the original object, allowing you to perform something
either before or after the request gets through to the original object.

In an ideal world, we’d want to put this code directly into our object’s class,
but that isn’t always possible. For instance, the class may be part of a closed 3rd-party library.

 The difference is that a Proxy usually manages the life cycle of its service object on its own,
 whereas the composition of Decorators is always controlled by the client.

 ref:
 1. https://www.geeksforgeeks.org/proxy-design-pattern/
 2. https://refactoring.guru/design-patterns/proxy
 */
public class ProxyDemo {
    public static void main (String[] args)
    {
        Internet internet = new ProxyInternet();
        try
        {
            internet.connectTo("geeksforgeeks.org");
            internet.connectTo("abc.com");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
