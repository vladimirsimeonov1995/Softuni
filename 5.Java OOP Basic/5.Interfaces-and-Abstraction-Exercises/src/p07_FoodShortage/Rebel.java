package p07_FoodShortage;

public class Rebel implements Buyer{

    private static final int FOOD = 5;

    private String name;
    private int age;
    private String group;
    private int foodEaten;



    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.foodEaten = 0;
    }

    @Override
    public int getFoodEaten() {
        return this.foodEaten;
    }


    @Override
    public void buyFood() {
        this.foodEaten = this.getFoodEaten() + FOOD;
    }

}
