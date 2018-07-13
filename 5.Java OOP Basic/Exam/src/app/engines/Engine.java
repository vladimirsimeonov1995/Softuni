package app.engines;

import app.entities.arenas.ArenaImpl;
import app.factories.ArenaFactory;
import app.factories.ComicCharacterFactory;
import app.factories.SuperPowerFactory;
import app.interfaces.comicWarInterfaces.Arena;
import app.interfaces.comicWarInterfaces.ComicCharacter;
import app.interfaces.comicWarInterfaces.Manager;
import app.interfaces.comicWarInterfaces.SuperPower;
import app.interfaces.io_interfaces.InputReader;
import app.interfaces.io_interfaces.OutputWriter;
import app.io.ConsoleReader;
import app.io.ConsoleWriter;
import app.utils.Constants;

import java.io.IOException;

public class Engine {

    private InputReader reader;
    private OutputWriter writer;
    private Manager manager;
    private ComicCharacterFactory characterFactory;
    private ArenaFactory arenaFactory;
    private SuperPowerFactory superPowerFactory;

    public Engine() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.manager = new WarManager();
        this.characterFactory = new ComicCharacterFactory();
        this.arenaFactory = new ArenaFactory();
        this.superPowerFactory = new SuperPowerFactory();

    }

    public void start() throws IOException {
        this.theWarOfTheComic();
    }

    public void theWarOfTheComic() throws IOException {

        String line = reader.readLine();

        while (!Constants.WAR_END_COMAND.equals(line)) {

            String[] cmdArgs = line.split(" ");

            switch (cmdArgs[0]) {

                case "CHECK_CHARACTER":
                    checkCharacter(cmdArgs[1]);
                    break;

                case "REGISTER_HERO":
                    registerHero(cmdArgs);
                    break;

                case "REGISTER_ANTI_HERO":
                    registerAntiHero(cmdArgs);
                    break;

                case "BUILD_ARENA":
                    addArena(cmdArgs);
                    break;

                case "SEND_HERO":
                    sendHero(cmdArgs);
                    break;

                case "SEND_ANTI_HERO":
                    sendAntiHero(cmdArgs);
                    break;

                case "SUPER_POWER":
                    superPowerCreate(cmdArgs);
                    break;

                case "ASSIGN_POWER":
                    assignPower(cmdArgs);
                    break;

                case "UNLEASH":
                    usePower(cmdArgs[1]);
                    break;

                    //COMICS_WAR Sofia
                case "COMICS_WAR":
                    comicsWar(cmdArgs[1]);
                    break;

            }


            line = reader.readLine();
        }

        writer.writeLine(manager.endWar());


    }


    private void comicsWar(String cmdArg) {
        String arena = cmdArg;
        String result = manager.startBattle(arena);

        writer.writeLine(result);
    }

    private void usePower(String cmdArg) {
        String characterName = cmdArg;
        String result = manager.usePowers(characterName);

        writer.writeLine(result);
    }

    private void assignPower(String[] cmdArgs) {
        String comicCharacterName = cmdArgs[1];
        String superPowerName = cmdArgs[2];

        String result = manager.assignSuperPowerToComicCharacter(comicCharacterName,superPowerName);

        writer.writeLine(result);
    }

    private void superPowerCreate(String[] cmdArgs) {
        String superPowerName = cmdArgs[1];
        double superPowerPoints = Double.parseDouble(cmdArgs[2]);

        SuperPower superPower = superPowerFactory.createSuperPower(superPowerName, superPowerPoints);

        if (superPower != null) {
            String result = manager.loadSuperPowerToPool(superPower);
            writer.writeLine(result);
        }
    }

    private void sendAntiHero(String[] cmdArgs) {
        String arenaName = cmdArgs[1];
        String antiHeroName = cmdArgs[2];
        String result = manager.addAntiHeroToArena(arenaName, antiHeroName);

        writer.writeLine(result);
    }

    private void sendHero(String[] cmdArgs) {
        String arenaName = cmdArgs[1];
        String heroName = cmdArgs[2];
        String result = manager.addHeroToArena(arenaName, heroName);

        writer.writeLine(result);
    }

    private void addArena(String[] cmdArgs) {
        String registerArenaName = cmdArgs[1];
        int registerArenaCapacity = Integer.parseInt(cmdArgs[2]);

        Arena newArena = arenaFactory.createArena(registerArenaName, registerArenaCapacity);

        String result = manager.addArena(newArena);

        writer.writeLine(result);
    }

    private void checkCharacter(String cmdArg) {
        String checkCharName = cmdArg;
        String result = manager.checkComicCharacter(checkCharName);

        writer.writeLine(result);
    }

    private void registerAntiHero(String[] cmdArgs) {
        String result;
        String createAntiHeroName = cmdArgs[1];
        String createAntiHeroType = cmdArgs[2];
        int createAntiHeroEnergy = Integer.parseInt(cmdArgs[3]);
        double creataAntiHeroHealth = Double.parseDouble(cmdArgs[4]);
        double createAntiHeroIntelligence = Double.parseDouble(cmdArgs[5]);
        double createAntiHeroHeroism = Double.parseDouble(cmdArgs[6]);

        ComicCharacter createdAntiHero = characterFactory.createCharacter(createAntiHeroType,
                createAntiHeroName, createAntiHeroEnergy,
                creataAntiHeroHealth, createAntiHeroIntelligence, createAntiHeroHeroism);

        if (createdAntiHero != null) {
            result = manager.addAntiHero(createdAntiHero);
            writer.writeLine(result);
        }
        return;
    }

    private void registerHero(String[] cmdArgs) {
        String result;
        String createHeroName = cmdArgs[1];
        String createHeroType = cmdArgs[2];
        int createHeroEnergy = Integer.parseInt(cmdArgs[3]);
        double createHeroHealth = Double.parseDouble(cmdArgs[4]);
        double createHeroIntelligence = Double.parseDouble(cmdArgs[5]);
        double createHeroHeroism = Double.parseDouble(cmdArgs[6]);

        ComicCharacter createdHero = characterFactory.createCharacter(createHeroType,
                createHeroName, createHeroEnergy,
                createHeroHealth, createHeroIntelligence, createHeroHeroism);

        if (createdHero != null) {
            result = manager.addHero(createdHero);
            writer.writeLine(result);
        }
    }

}
