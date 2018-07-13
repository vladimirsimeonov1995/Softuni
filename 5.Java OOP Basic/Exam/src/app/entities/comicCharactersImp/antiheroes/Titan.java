package app.entities.comicCharactersImp.antiheroes;

public class Titan extends AntiHero {


    public Titan(String name, int energy, double health, double intelligence, double evilness) {
        super(name, energy, health, intelligence, evilness);
    }


    @Override
    public double attack() {
        //(energy + intelligence + special)  * 3
        double attackPoints = (super.getEnergy() + super.getIntelligence() + super.getSpecial()) * 3;

        return attackPoints;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("####Titan Attack Power: %.2f",this.attack()));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

}
