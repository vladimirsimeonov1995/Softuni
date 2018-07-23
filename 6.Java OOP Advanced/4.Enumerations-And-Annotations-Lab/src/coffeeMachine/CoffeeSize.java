package coffeeMachine;

public enum CoffeeSize {

    SMALL(50,50), NORMAL(75,100), DOUBLE(200,100);

    int dosage;
    int price;

    CoffeeSize(int dosage, int price){
        this.dosage = dosage;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
