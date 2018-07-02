package p03_WildFarm.Animals;

import p03_WildFarm.Foods.Food;
import p03_WildFarm.Foods.Meat;

public class Tiger extends Felime {


    public Tiger(String animalName, String animalType,
                    Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        this.makeSound();
        if(food instanceof Meat){
            super.setFoodEaten((super.getFoodEaten() + food.getQuantity()));
        }else {
            System.out.println("Tigers are not eating that type of food!");
        }
    }
}
