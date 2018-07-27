package p10_InfernoInfinity.entities.gems;

public abstract class Gem {

    private int strength;
    private int agility;
    private int vitality;

    public Gem(int strength, int agility, int vitality) {
        this.setStrength(strength);
        this.setAgility(agility);
        this.setVitality(vitality);
    }

    public int getStrength() {
        return this.strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return this.agility;
    }

    private void setAgility(int agility) {
        this.agility = agility;
    }

    public int getVitality() {
        return this.vitality;
    }

    private void setVitality(int vitality) {
        this.vitality = vitality;
    }


}
