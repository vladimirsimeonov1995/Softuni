package p03_AnimalFarm;

import java.text.DecimalFormat;

public class Chicken {

    private static final DecimalFormat formater = new DecimalFormat("#.##");
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if(!name.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.name = name;
    }

    private  void setAge(int age) {
        if(age < 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }

        this.age = age;
    }

    public String productPerDay(){
        return String.format("Chicken %s (age %d) can produce %s eggs per day.",
                this.name,this.age,formater.format(calculateProductPerDay()));


    }

    private double calculateProductPerDay(){
        double eggPerDay = 0;
        if(this.age <= 5 )
            eggPerDay = 2;
        else if(this.age <= 11)
            eggPerDay = 1;
        else
            eggPerDay = 0.75;


        return eggPerDay;



    }
}
