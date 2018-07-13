package app.entities.comicCharactersImp;

import app.interfaces.comicWarInterfaces.ComicCharacter;
import app.interfaces.comicWarInterfaces.SuperPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ComicCharacterImpl implements ComicCharacter {

    private String name;
    private int energy;
    private double health;
    private double intelligence;
    private List<SuperPower> powers;

    protected ComicCharacterImpl(String name, int energy, double health, double intelligence) {
        this.setName(name);
        this.setEnergy(energy);
        this.setHealth(health);
        this.setIntelligence(intelligence);
        this.powers = new ArrayList<>();
    }

    private void setName(String name) {
        if(!name.matches("[\\w]+"))
            throw new IllegalArgumentException("Comic Character name is not in the correct format!");
        if(name.length() > 12 || name.length() <2)
            throw new IllegalArgumentException("Comic Character name is not in the correct format!");

        this.name = name;
    }

    private void setEnergy(int energy) {
        if(energy < 0 || energy > 300)
            throw new IllegalArgumentException("Energy is not in the correct range!");

        this.energy = energy;
    }

    private void setHealth(double health) {
        if(health < 0)
            throw new IllegalArgumentException("Health should be a possitive number!");

        this.health = health;
    }

    private void setIntelligence(double intelligence) {
        if(intelligence < 0 || intelligence > 200)
            throw new IllegalArgumentException("Intelligence is not in the correct range!");

        this.intelligence = intelligence;
    }

    public void takeDamage(double damage){
        this.health -= damage;
    }

    public void boostCharacter(int energy,double health,double inteligence){
        this.setEnergy(energy);
        this.setHealth(health);
        this.setIntelligence(inteligence);
    }

    public String getName(){
        return this.name;
    }

    public int getEnergy(){
        return this.energy;
    }

    public double getHealth(){
        return this.health;
    }

    public double getIntelligence(){
        return this.intelligence;
    }

    public List<SuperPower> getPowers() {
        return Collections.unmodifiableList(powers);
    }

    public String useSuperPowers(){

        if(this.powers.isEmpty())
            return String.format("%s has no super powers!\n",this.getName());

        for (SuperPower power : powers) {
            this.energy += power.getPowerPoints();
            this.health += (power.getPowerPoints() ) *2;
        }

        return String.format("%s used his super powers!\n",this.getName());


    }

    @Override
    public void addSuperPower(SuperPower superPower) {
        this.powers.add(superPower);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Name: ").append(this.getName()).append(System.lineSeparator());
        sb.append(String.format("##Health: %.2f// Energy: %d// Intelligence: %.2f"
                ,this.getHealth(),this.getEnergy(),this.getIntelligence()));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
