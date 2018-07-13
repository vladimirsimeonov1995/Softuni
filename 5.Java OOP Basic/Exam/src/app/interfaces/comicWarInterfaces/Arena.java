package app.interfaces.comicWarInterfaces;

import java.util.Collections;
import java.util.Map;

public interface Arena {

    String getArenaName();

    boolean isArenaFull();

    void addHero(ComicCharacter hero);

    void addAntiHero(ComicCharacter antiHero);

    boolean fightHeroes();

    Map<String, ComicCharacter> getHeroes();

    Map<String, ComicCharacter> getAntiHeroes();

    void boostHero(ComicCharacter hero);

    void boostAntiHero(ComicCharacter antiHero);

    public void addSuperPowerToHero(String comicCharacter, SuperPower superPower);

    public void addSuperPowerToAntiHero(String comicCharacter, SuperPower superPower);

    public void activateSuperPower(String comicCharacter, String type);


}
