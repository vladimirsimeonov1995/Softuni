package app.entities.comicCharactersImp.heroes;

public class MarvelHero extends Hero {


    public MarvelHero(String name, int energy, double health, double intelligence, double heroism) {
        super(name, energy, health, intelligence, heroism);
    }


    @Override
    public double attack() {
        //((energy + special) * intelligence) / 2.5
        double attackPoints = ((super.getEnergy() + super.getSpecial()) * super.getIntelligence()) / 2.5;

        return attackPoints;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("####Marvel Attack Power: %.2f",this.attack()));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
