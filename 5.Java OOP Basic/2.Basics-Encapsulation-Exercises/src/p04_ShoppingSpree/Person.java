package p04_ShoppingSpree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {

    private String name;
    private int money;
    private List<Product> bagOfProducts;

    public Person(String name, int money) {
        this.setName(name);
        this.setMoney(money);
        this.bagOfProducts = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null || name.isEmpty() || name.matches("\\s+"))
            throw new IllegalArgumentException("Name cannot be empty");

        this.name = name;
    }

    private void setMoney(int money) {
        if(money < 0)
            throw new IllegalArgumentException("Money cannot be negative");

        this.money = money;
    }

    public List<Product> getBagOfProducts() {
        return Collections.unmodifiableList(this.bagOfProducts);
    }

    public String addProduct(Product product){
        if(this.money >= product.getCost()){
            this.bagOfProducts.add(product);
            this.money -= product.getCost();
            return String.format("%s bought %s",this.name,product.getName());
        }else {
            return String .format("%s can't afford %s",this.name,product.getName());
        }
    }

}
