package p04_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private int cost;

    public Product(String name, int cost) {
        this.setName(name);
        this.setCost(cost);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if(name == null || name.isEmpty() || name.matches("\\s+"))
            throw new IllegalArgumentException("Name cannot be empty");

        this.name = name;
    }

    public int getCost() {
        return this.cost;
    }

    private void setCost(int cost) {
        if(cost < 0)
            throw new IllegalArgumentException("Money cannot be negative");


        this.cost = cost;
    }
}
