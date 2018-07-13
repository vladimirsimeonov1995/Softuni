package app.entities.comicCharactersImp.antiheroes;

import app.entities.comicCharactersImp.ComicCharacterImpl;

public abstract class AntiHero extends ComicCharacterImpl {

    private double evilness;


    protected AntiHero(String name, int energy, double health, double intelligence, double evilness) {
        super(name, energy, health, intelligence);
        this.setEvilness(evilness);
    }

    private void setEvilness(double evilness) {
        if(this.evilness < 0)
            throw new IllegalArgumentException("Evilness should be a possitive number!");

        this.evilness = evilness;
    }

    @Override
    public double getSpecial() {
        return this.evilness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString()).append(String.format("###Evilness: %.2f",this.evilness));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
