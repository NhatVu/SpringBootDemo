package com.example.demo.pattern.structural.compositev2;

import java.util.ArrayList;
import java.util.List;

public class BoxComposite implements Product{
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product){
        productList.add(product);
    }

    @Override
    public int getPrice() {
        int total = 0;
        for (Product p: productList){
            total += p.getPrice();
        }

        return total;
    }
}
