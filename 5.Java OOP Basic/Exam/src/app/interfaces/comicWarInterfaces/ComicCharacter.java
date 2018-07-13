package app.interfaces.comicWarInterfaces;

import java.util.List;

public interface ComicCharacter {

    void takeDamage(double damage);

    String getName();

    void boostCharacter(int energy, double health, double intelligence);

    int getEnergy();

    double getHealth();

    double getIntelligence();

    double attack();

    double getSpecial();

    public List<SuperPower> getPowers();

    String useSuperPowers();

    void addSuperPower(SuperPower superPower);




}
