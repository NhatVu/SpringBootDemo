package com.example.demo.pattern.structural.compositev2;

public class CompositeV2Demo {
    public static void main(String[] args) {
        // box 1
        Pencil p1 = new Pencil(1);
        BoxComposite box1 = new BoxComposite();
        box1.addProduct(p1);

        // box 2
        Pencil p2 = new Pencil(2);
        Car c2 = new Car(5);
        BoxComposite b2 = new BoxComposite();
        b2.addProduct(p2);
        b2.addProduct(c2);

        // large box
        BoxComposite large = new BoxComposite();
        large.addProduct(box1);
        large.addProduct(b2);

        System.out.println("total price: " + large.getPrice());

    }
}
