package app.engines;

import app.factories.SuperPowerFactory;
import app.interfaces.comicWarInterfaces.Arena;
import app.interfaces.comicWarInterfaces.ComicCharacter;
import app.interfaces.comicWarInterfaces.Manager;
import app.interfaces.comicWarInterfaces.SuperPower;
import app.utils.Constants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WarManager implements Manager {


    private Map<String, Arena> arenas;
    private Map<String, ComicCharacter> heroes;
    private Map<String, ComicCharacter> antiHeroes;
    private Map<String, SuperPower> superPowers;
    private int heroesBattleWon;
    private int antiHeroesBattleWon;


    public WarManager() {
        this.arenas = new LinkedHashMap<>();
        this.heroes = new LinkedHashMap<>();
        this.antiHeroes = new LinkedHashMap<>();
        this.superPowers = new LinkedHashMap<>();
        this.heroesBattleWon = 0;
        this.antiHeroesBattleWon = 0;

    }


    @Override
    public String checkComicCharacter(String characterName) {
        if (!antiHeroes.containsKey(characterName) && !heroes.containsKey(characterName)) {
            return String.format("Sorry, fans! %s doesn't exist in our comics\n", characterName);
        } else if (antiHeroes.containsKey(characterName)) {
            if (antiHeroes.get(characterName).getHealth() < 0)
                return String.format("%s has fallen in battle!\n", characterName);

            return antiHeroes.get(characterName).toString();

        } else {
            if (heroes.get(characterName).getHealth() < 0)
                return String.format("%s has fallen in battle!\n", characterName);

            return heroes.get(characterName).toString();
        }
    }

    @Override
    public String addHero(ComicCharacter hero) {
        if (heroes.containsKey(hero.getName())) {
            try {
                heroes.get(hero.getName()).boostCharacter(hero.getEnergy(), hero.getHealth(), hero.getIntelligence());
                return String.format("%s evolved!\n", hero.getName());
            } catch (IllegalArgumentException ex) {
                return ex.getMessage();
            }
        } else {
            try {

                for (Arena arena : arenas.values()) {
                    if (arena.getHeroes().containsKey(hero.getName())) {
                        arena.boostHero(hero);
                        return String.format("%s evolved!\n", hero.getName());
                    }
                }
            } catch (IllegalArgumentException ex) {
                return ex.getMessage();
            }
        }

        this.heroes.put(hero.getName(), hero);
        return String.format("%s is ready for battle!\n", hero.getName());
    }

    @Override
    public String addAntiHero(ComicCharacter antiHero) {
        if (antiHeroes.containsKey(antiHero.getName())) {
            try {
                antiHeroes.get(antiHero.getName()).boostCharacter(antiHero.getEnergy(),
                        antiHero.getHealth(), antiHero.getIntelligence());
                return String.format("%s evolved!\n", antiHero.getName());
            } catch (IllegalArgumentException ex) {
                return ex.getMessage();
            }
        } else {
            try {

                for (Arena arena : arenas.values()) {
                    if (arena.getAntiHeroes().containsKey(antiHero.getName())) {
                        arena.boostAntiHero(antiHero);
                        return String.format("%s evolved!\n", antiHero.getName());
                    }
                }
            } catch (IllegalArgumentException ex) {
                return ex.getMessage();
            }
        }

        this.antiHeroes.put(antiHero.getName(), antiHero);
        return String.format("%s is ready for battle!\n", antiHero.getName());
    }

    @Override
    public String addArena(Arena arena) {
        if (arenas.containsKey(arena.getArenaName()))
            return "A battle is about to start there!\n";

        arenas.put(arena.getArenaName(), arena);
        return String.format("%s is becoming a fighting ground!\n", arena.getArenaName());
    }

    @Override
    public String addHeroToArena(String arena, String hero) {

        ComicCharacter currentHero = null;

        for (Arena arena1 : arenas.values()) {
            if (arena1.getHeroes().containsKey(hero))
                return String.format("%s is fighting!\n", hero);
        }

        if (heroes.get(hero).getHealth() <= 0)
            return String.format("%s is dead!\n", hero);

        if (arenas.get(arena).isArenaFull()) {
            return "Arena is full!\n";
        }

        arenas.get(arena).addHero(heroes.get(hero));
        return String.format("%s is fighting for your freedom in %s!\n", hero, arena);


    }

    @Override
    public String addAntiHeroToArena(String arena, String antiHero) {
        ComicCharacter currentAntiHero = null;

        for (Arena arena1 : arenas.values()) {
            if (arena1.getAntiHeroes().containsKey(antiHero))
                return String.format("%s is fighting!\n", antiHero);
        }

        if (antiHeroes.get(antiHero).getHealth() <= 0)
            return String.format("%s is dead!\n", antiHero);

        if (arenas.get(arena).isArenaFull()) {
            return "Arena is full!\n";
        }

        arenas.get(arena).addAntiHero(antiHeroes.get(antiHero));
        return String.format("%s and his colleagues are trying to take over %s!\n", antiHero, arena);
    }

    @Override
    public String loadSuperPowerToPool(SuperPower superPower) {
        if (superPowers.containsKey(superPower.getName()))
            return "This super power already exists!\n";

        superPowers.put(superPower.getName(), superPower);
        return String.format("%s added to pool!\n", superPower.getName());
    }

    @Override
    public String assignSuperPowerToComicCharacter(String comicCharacter, String superPower) {
        if (!superPowers.containsKey(superPower))
            return String.format("%s already assigned!\n", superPower);

        if (heroes.containsKey(comicCharacter)) {
            heroes.get(comicCharacter).addSuperPower(superPowers.get(superPower));
            for (Arena arena : arenas.values()) {
                if (arena.getHeroes().containsKey(comicCharacter))
                    arena.addSuperPowerToHero(comicCharacter, superPowers.get(superPower));
            }
            superPowers.remove(superPower);
            return String.format("%s has a new super power!\n",comicCharacter);
        } else {
            antiHeroes.get(comicCharacter).addSuperPower(superPowers.get(superPower));
            for (Arena arena : arenas.values()) {
                if (arena.getAntiHeroes().containsKey(comicCharacter))
                    arena.addSuperPowerToAntiHero(comicCharacter, superPowers.get(superPower));
            }
            superPowers.remove(superPower);
            return String.format("%s has a new super power!\n",comicCharacter);
        }


    }

    @Override
    public String usePowers(String characterName) {
        if(heroes.containsKey(characterName)){

            if(heroes.get(characterName).getPowers().size() == 0)
                return String.format("%s has no super powers!",characterName);

            for (Arena arena : arenas.values()) {
                arena.activateSuperPower(characterName,Constants.HERO);
            }
            return heroes.get(characterName).useSuperPowers();
        }else {
            if(antiHeroes.get(characterName).getPowers().size() == 0)
                return String.format("%s has no super powers!",characterName);

            for (Arena arena : arenas.values()) {
                arena.activateSuperPower(characterName,Constants.ANTI_HERO);
            }
            return antiHeroes.get(characterName).useSuperPowers();
        }
    }

    @Override
    public String startBattle(String arena) {
        Arena battleArena = arenas.get(arena);

        if(battleArena.getHeroes().size() + battleArena.getAntiHeroes().size() == 0)
            return "SAFE ZONE!\n";

        boolean check = battleArena.fightHeroes();

        for (ComicCharacter hero : arenas.get(arena).getHeroes().values()) {
            this.heroes.put(hero.getName(),hero);
        }

        for (ComicCharacter antiHero : arenas.get(arena).getAntiHeroes().values()) {
            this.antiHeroes.put(antiHero.getName(),antiHero);
        }

        arenas.remove(arena);

        if(check){
            this.heroesBattleWon ++;
            return String.format("Heroes won the battle of %s!\n",arena);

        }
        this.antiHeroesBattleWon ++;
        return String.format("Anti Heroes won the battle of %s\n",arena);
    }

    @Override
    public String endWar() {
        if(this.heroesBattleWon > this.antiHeroesBattleWon)
            return String.format("After %d battles our FRIENDLY HEROES WON!",this.heroesBattleWon);

        return "WE ARE DOOMED!";
    }
}
