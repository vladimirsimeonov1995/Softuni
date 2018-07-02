package p03_WildFarm.Animals;

import p03_WildFarm.Foods.Food;
import p03_WildFarm.Foods.Vegetable;

public class Zebra extends Mammal {


    public Zebra(String animalName, String animalType,
                    Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        this.makeSound();
        if(food instanceof Vegetable){
            super.setFoodEaten((super.getFoodEaten() + food.getQuantity()));
        }else {
            System.out.println("Zebras are not eating that type of food!");
        }
    }
}
