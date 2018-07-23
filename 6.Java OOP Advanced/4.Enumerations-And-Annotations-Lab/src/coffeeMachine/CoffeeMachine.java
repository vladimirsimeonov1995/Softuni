package coffeeMachine;

import codingTracker.Author;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeMachine {

    private List<Coffee> coffees;
    private int coints ;

    public CoffeeMachine(){
        this.coffees = new ArrayList<>();
        this.coints = 0 ;
    }

    @Author(name = "Vladi")
    public void insertCoin(String coin){
        coints += Enum.valueOf(Coin.class, coin.toUpperCase()).getValue();
    }

    @Author(name = "Pesho")
    public void buyCoffee(String size, String type){

        Coffee coffee = new Coffee(size,type);

        if(this.coints >= coffee.getCoffeePrice()) {
            this.coffees.add(coffee);
            this.coints = 0;
        }
    }

    @Author(name = "Gosho")
    public Iterable<Coffee> coffeesSold(){
        return Collections.unmodifiableCollection(coffees);
    }


}
