package app.factories;

import app.entities.comicCharactersImp.antiheroes.Titan;
import app.entities.comicCharactersImp.antiheroes.Villain;
import app.entities.comicCharactersImp.heroes.DCHero;
import app.entities.comicCharactersImp.heroes.MarvelHero;
import app.interfaces.comicWarInterfaces.ComicCharacter;
import app.interfaces.io_interfaces.OutputWriter;
import app.io.ConsoleWriter;

public class ComicCharacterFactory {

    private OutputWriter writer;

    public ComicCharacterFactory(){
        this.writer = new ConsoleWriter();
    }

    public ComicCharacter createCharacter(String type, String name, int energy, double health, double intelligence, double speciality){

        switch (type){
            case "DCHero":
                try {
                    return new DCHero(name,energy,health,intelligence,speciality);
                }catch (IllegalArgumentException ex){
                    writer.writeNewLine(ex.getMessage());
                }
                break;
            case "MarvelHero":
                try {
                    return new MarvelHero(name,energy,health,intelligence,speciality);
                }catch (IllegalArgumentException ex){
                    writer.writeNewLine(ex.getMessage());
                }
                break;
            case "Villain":
                try {
                    return new Villain(name,energy,health,intelligence,speciality);
                }catch (IllegalArgumentException ex){
                    writer.writeNewLine(ex.getMessage());
                }
                break;
            case "Titan":
                try {
                    return new Titan(name,energy,health,intelligence,speciality);
                }catch (IllegalArgumentException ex){
                    writer.writeNewLine(ex.getMessage());
                }
                break;
        }

        return null;
    }

}
