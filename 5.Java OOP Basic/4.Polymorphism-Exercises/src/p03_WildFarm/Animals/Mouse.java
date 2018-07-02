package p03_WildFarm.Animals;

import p03_WildFarm.Foods.Food;
import p03_WildFarm.Foods.Vegetable;

public class Mouse extends Mammal {


    public Mouse(String animalName, String animalType,
                    Double animalWeight,  String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        this.makeSound();
        if(food instanceof Vegetable){
            super.setFoodEaten((super.getFoodEaten() + food.getQuantity()));
        }else {
            System.out.println("Mouses are not eating that type of food!");
        }
    }

}
