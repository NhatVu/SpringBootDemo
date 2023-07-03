package com.example.demo.pattern.structural.bridge;

/**
 * Bridge is a structural design pattern that lets you split a large class or a set of closely related classes
 * into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.
 *
 * For example: if we have 2 dimensions: Shape + Color
 * Then we have: RedCircle, RedSquare, BlueCirle, BlueSquare .... adding 1 dimension -> create exponential class
 *
 * Using Bridge, treat Shape as abstraction and Color as platform (implementation) -> separate 2 dimension.
 * That reference will act as a bridge between the Shape and Color classes
 *
 * Normlly, implementation will be different database (mysql, postgres, ..) or difference platform like linux, window, mac, ..
 * The logic in abstraction will be the same, but the actual work will be delegate to implementation
 */
public class BridgeDemo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
