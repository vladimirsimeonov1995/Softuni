package p03_WildFarm.Animals;

import p03_WildFarm.Foods.Food;
import p03_WildFarm.Foods.Vegetable;

public class Cat extends Felime {

    private String breed;

    public Cat(String animalName, String animalType,
                  Double animalWeight,
                  String livingRegion, String breed) {

        super(animalName, animalType, animalWeight,  livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        this.makeSound();
        super.setFoodEaten((super.getFoodEaten() + food.getQuantity()));
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]",
                super.getAnimalType(), super.getAnimalName(),
                this.breed, formatter.format(this.getAnimalWeight()),
                this.getLivingRegion(),
                super.getFoodEaten());
    }
}
