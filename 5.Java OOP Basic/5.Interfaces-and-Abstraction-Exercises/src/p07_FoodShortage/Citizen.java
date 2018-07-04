package p07_FoodShortage;

public class Citizen implements Enterable, Birthable, Buyer {

    private static final int FOOD = 10;

    private String name;
    private Integer age;
    private String id;
    private String birthDate;
    private int foodEaten;

    public Citizen(String name, Integer age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
        this.foodEaten = 0;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
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
