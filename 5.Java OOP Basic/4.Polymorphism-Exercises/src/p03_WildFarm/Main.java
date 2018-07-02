package p03_WildFarm;


import p03_WildFarm.Animals.*;
import p03_WildFarm.Foods.Food;
import p03_WildFarm.Foods.Meat;
import p03_WildFarm.Foods.Vegetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animals = new ArrayList<>();

        while (true){
            String[] animalInfo = reader.readLine().split(" ");
            if("End".equals(animalInfo[0]))
                break;

            getAnimal(animals, animalInfo);

            String[] foodInfo = reader.readLine().split(" ");
            if("End".equals(foodInfo[0]))
                break;


            getFood(animals, foodInfo);

        }

        print(animals);


    }

    private static void getFood(List<Animal> animals, String[] foodInfo) {
        if("Vegetable".equals(foodInfo[0])){
            Food food = new Vegetable(Integer.parseInt(foodInfo[1]));
            Animal currentAnimal = animals.get(animals.size() -1);
            currentAnimal.eat(food);
        }
        else {
            Food food = new Meat(Integer.parseInt(foodInfo[1]));
            Animal currentAnimal = animals.get(animals.size());
            currentAnimal.eat(food);
        }
    }

    private static void print(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }


    private static void getAnimal(List<Animal> animals, String[] animalInfo) {
        String animalType = animalInfo[0];
        String animalName = animalInfo[1];
        double animalWeight = Double.parseDouble(animalInfo[2]);
        String animalLivingRegion = animalInfo[3];
        String catBreed = null;
        try {
            catBreed = animalInfo[4];
        }catch (ArrayIndexOutOfBoundsException ignored){
        }

        switch (animalType){
            case "Mouse":
                animals.add(new Mouse(animalName,animalType,animalWeight,animalLivingRegion));
                break;
            case "Cat":
                animals.add(new Cat(animalName,animalType,animalWeight,animalLivingRegion,catBreed));
                break;
            case "Tiger":
                animals.add((new Tiger(animalName,animalType,animalWeight,animalLivingRegion)));
                break;
            case "Zebra":
                animals.add((new Zebra(animalName,animalType,animalWeight,animalLivingRegion)));
                break;
        }
    }

}
