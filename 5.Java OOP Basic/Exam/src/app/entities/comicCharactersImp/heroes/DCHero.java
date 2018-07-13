package app.entities.comicCharactersImp.heroes;

public class DCHero extends Hero {


    public DCHero(String name, int energy, double health, double intelligence, double heroism) {
        super(name, energy, health, intelligence, heroism);
    }

    @Override
    public double attack() {
        //= energy / 1.5 + special + intelligence
        double attackPoints = ((double) super.getEnergy() / 1.5) + super.getSpecial() + super.getIntelligence();

        return attackPoints;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("####DC Attack Power: %.2f",this.attack()));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
