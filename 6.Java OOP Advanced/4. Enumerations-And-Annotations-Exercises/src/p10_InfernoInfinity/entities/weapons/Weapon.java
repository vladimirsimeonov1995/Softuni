package p10_InfernoInfinity.entities.weapons;

import p10_InfernoInfinity.anotations.WeaponAnotation;
import p10_InfernoInfinity.entities.gems.Gem;


@WeaponAnotation(author = "Pesho",revision = 3,description = "Used for Java OOP Advanced course - Enumerations and Annotations.",reviewers = {"Pesho", "Svetlio"})
public abstract class Weapon implements Comparable<Weapon> {

    private String name;
    private int minDMG;
    private int maxDMG;
    private int numberOfSockets;
    private int strength;
    private int agility;
    private int vitality;
    private Gem[] gems;

    public Weapon(String name, int minDMG, int maxDMG, int numberOfSockets) {
        this.setName(name);
        this.setMinDMG(minDMG);
        this.setMaxDMG(maxDMG);
        this.setNumberOfSockets(numberOfSockets);
        this.strength = 0;
        this.agility = 0;
        this.vitality = 0;
        this.gems = new Gem[this.getNumberOfSockets()];
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getMinDMG() {
        return this.minDMG;
    }

    private void setMinDMG(int minDMG) {
        this.minDMG = minDMG;
    }

    public int getMaxDMG() {
        return this.maxDMG;
    }

    private void setMaxDMG(int maxDMG) {
        this.maxDMG = maxDMG;
    }

    public int getNumberOfSockets() {
        return this.numberOfSockets;
    }

    private void setNumberOfSockets(int numberOfSockets) {
        this.numberOfSockets = numberOfSockets;
    }

    public void addGem(Gem gem, int position){

        if(position < numberOfSockets && position >= 0){

            if(gems[position] != null)
                this.removeGem(position);

            gems[position] = gem;

            this.strength += gem.getStrength();
            this.agility += gem.getAgility();
            this.vitality += gem.getVitality();

            this.minDMG += (2*gem.getStrength()) + gem.getAgility();
            this.maxDMG += (3*gem.getStrength()) + (4*gem.getAgility());
        }
    }

    public void removeGem(int position) {

        if(position < numberOfSockets && position >= 0){

            Gem gem = gems[position];
            this.gems[position] = null;

            this.strength -= gem.getStrength();
            this.agility -= gem.getAgility();
            this.vitality -= gem.getVitality();

            this.minDMG -= (2*gem.getStrength()) + gem.getAgility();
            this.maxDMG -= (3*gem.getStrength()) + (4*gem.getAgility());

        }

    }

    public double getItemLevel(){
        return ((((double)this.getMinDMG() + (double)this.getMaxDMG())/2) + this.strength + this.agility + this.vitality) ;
    }

    @Override
    public int compareTo(Weapon secondWeapon) {
        return Double.compare(this.getItemLevel(),secondWeapon.getItemLevel());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //Axe of Misfortune: 21-39 Damage, +7 Strength, +2 Agility, +5 Vitality

        sb.append(this.getName()).append(": ").append(this.getMinDMG())
                .append("-").append(this.getMaxDMG()).append(" Damage, +").append(this.strength)
                .append(" Strength, +").append(this.agility).append(" Agility, +")
                .append(this.vitality).append(" Vitality")
                .append(" (Item Level: ").append(String.format("%.1f",this.getItemLevel())).append(")");
        return sb.toString();
    }



}
