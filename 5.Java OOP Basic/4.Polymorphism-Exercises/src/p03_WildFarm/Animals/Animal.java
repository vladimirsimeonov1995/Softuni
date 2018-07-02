package p03_WildFarm.Animals;

import p03_WildFarm.Foods.Food;

import java.text.DecimalFormat;

public abstract class Animal {


    protected final DecimalFormat formatter = new DecimalFormat("#.#######");

    private String animalName;
    private String animalType;
    private Double animalWeight;
    private int foodEaten;

    protected Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
    }

    protected String getAnimalName() {
        return this.animalName;
    }

    protected String getAnimalType() {
        return this.animalType;
    }

    protected Double getAnimalWeight() {
        return this.animalWeight;
    }

    protected int getFoodEaten() {
        return this.foodEaten;
    }

    protected void setFoodEaten(int foodEaten) {
        this.foodEaten = foodEaten;
    }

    public abstract void makeSound();

    public abstract void eat(Food food);


}
