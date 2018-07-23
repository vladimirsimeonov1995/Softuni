package coffeeMachine;

public class Coffee {

    private CoffeeSize coffeeSize;
    private CoffeeType coffeeType;

    public Coffee(String coffeeSize, String coffeeType) {
        this.coffeeSize = Enum.valueOf(CoffeeSize.class, coffeeSize.toUpperCase());
        this.coffeeType = Enum.valueOf(CoffeeType.class, coffeeType.toUpperCase());
    }

    public int getCoffeePrice(){
        return this.coffeeSize.price;
    }

    @Override
    public String toString() {
        return this.coffeeSize + " " + this.coffeeType;
    }
}
