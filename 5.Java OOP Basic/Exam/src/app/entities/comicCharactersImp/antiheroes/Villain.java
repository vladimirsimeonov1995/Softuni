package app.entities.comicCharactersImp.antiheroes;

public class Villain extends AntiHero {


    public Villain(String name, int energy, double health, double intelligence, double evilness) {
        super(name, energy, health, intelligence, evilness);
    }


    @Override
    public double attack() {
        //(intelligence * special) / energy
        double attackPoints = (super.getIntelligence() * super.getSpecial()) / (double)super.getEnergy();

        return attackPoints;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("####Villain Attack Power: %.2f",this.attack()));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

}
