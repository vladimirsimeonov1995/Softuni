package app.entities.arenas;

import app.engines.WarManager;
import app.entities.comicCharactersImp.antiheroes.AntiHero;
import app.entities.comicCharactersImp.heroes.Hero;
import app.interfaces.comicWarInterfaces.Arena;
import app.interfaces.comicWarInterfaces.ComicCharacter;
import app.interfaces.comicWarInterfaces.Manager;
import app.interfaces.comicWarInterfaces.SuperPower;

import java.util.*;
import java.util.stream.Collectors;

public class ArenaImpl implements Arena {


    private String arenaName;
    private Map<String ,ComicCharacter> heroes;
    private Map<String ,ComicCharacter> antiHeroes;
    private int capacity;

    public ArenaImpl(String arenaName, int capacity) {
        this.arenaName = arenaName;
        this.capacity = capacity;
        this.heroes = new LinkedHashMap<>();
        this.antiHeroes = new LinkedHashMap<>();
    }

    @Override
    public Map<String, ComicCharacter> getHeroes() {
        return Collections.unmodifiableMap(heroes);
    }

    @Override
    public Map<String, ComicCharacter> getAntiHeroes() {
        return Collections.unmodifiableMap(antiHeroes);
    }

    @Override
    public String getArenaName() {
        return this.arenaName;
    }

    public void boostHero(ComicCharacter hero){
        heroes.get(hero.getName()).boostCharacter(hero.getEnergy(),hero.getHealth(),hero.getIntelligence());
    }

    public void boostAntiHero(ComicCharacter antiHero){
        heroes.get(antiHero.getName()).boostCharacter(antiHero.getEnergy(),antiHero.getHealth(),antiHero.getIntelligence());
    }

    @Override
    public boolean isArenaFull() {
        if(heroes.size() + antiHeroes.size() == capacity){
            return true;
        }

        return false;
    }

    @Override
    public void addHero(ComicCharacter hero) {
        this.heroes.put(hero.getName(),hero);
    }

    @Override
    public void addAntiHero(ComicCharacter antiHero) {
        this.antiHeroes.put(antiHero.getName(),antiHero);
    }

    @Override
    public void addSuperPowerToHero(String comicCharacter, SuperPower superPower){
        this.heroes.get(comicCharacter).addSuperPower(superPower);
    }

    @Override
    public void addSuperPowerToAntiHero(String comicCharacter, SuperPower superPower){
        this.antiHeroes.get(comicCharacter).addSuperPower(superPower);
    }

    @Override
    public void activateSuperPower(String comicCharacter, String type){
        if(type.equals("Hero")) {
            for (ComicCharacter character : heroes.values()) {
                if (character.getName().equals(comicCharacter))
                    character.useSuperPowers();
            }
        }else {
            for (ComicCharacter character : antiHeroes.values()) {
                if (character.getName().equals(comicCharacter))
                    character.useSuperPowers();
            }
        }
    }

    @Override
    public boolean fightHeroes() {


        List<ComicCharacter> deadHeroes = new ArrayList<>();
        List<ComicCharacter> deadAntHeroes = new ArrayList<>();

        while (true){

            if(heroes.size() > antiHeroes.size()) {
                this.round(antiHeroes, heroes,deadHeroes,deadAntHeroes,"anti");
            }
            else {
                this.round(heroes, antiHeroes,deadHeroes,deadAntHeroes,"hero");
            }


            for (ComicCharacter deadAntHero : deadAntHeroes) {
                this.antiHeroes.put(deadAntHero.getName(),deadAntHero);
            }
            for (ComicCharacter deadHero : deadHeroes) {
                this.heroes.put(deadHero.getName(),deadHero);
            }

            if(heroes.size() == 0 )
                return false;


            return true;

        }

    }

    public void round(Map<String ,ComicCharacter> attackers
            , Map<String ,ComicCharacter> deffending
            ,List<ComicCharacter> deadHeroes , List<ComicCharacter> deadAntiHeroes, String currentAttakers ){

        int fights = 5;

        if(attackers.size() < 5 )
            fights = attackers.size();
        if(deffending.size() < attackers.size())
            if(deffending.size() < 5)
                fights = deffending.size();

        List<String> namesAtackers = new ArrayList<>(attackers.keySet());
        List<String> namesDeffenders = new ArrayList<>(deffending.keySet());

        for (int i = 0; i < fights; i++) {

            ComicCharacter attacker = attackers.get(namesAtackers.get(0));
            namesAtackers.remove(0);
            attackers.remove(attacker.getName());

            ComicCharacter defender = deffending.get(namesDeffenders.get(0));
            namesDeffenders.remove(0);
            deffending.remove(defender.getName());

            defender.takeDamage(attacker.attack());

            if(defender.getHealth() > 0)
                deffending.put(defender.getName(),defender);
            else {

                if (currentAttakers.equals("anti"))
                    deadHeroes.add(defender);
                else {
                    deadAntiHeroes.add(defender);
                }
            }

            attackers.put(attacker.getName(),attacker);
        }


        if(currentAttakers.equals("anti"))
            currentAttakers = "hero";
        else
            currentAttakers = "anti";

        if(deffending.size() > 0)
            this.round(deffending,attackers,deadHeroes,deadAntiHeroes,currentAttakers);

    }


}
