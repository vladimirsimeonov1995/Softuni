package app.factories;

import app.entities.arenas.ArenaImpl;
import app.interfaces.comicWarInterfaces.Arena;

public class ArenaFactory {

    public ArenaFactory(){}

    public Arena createArena(String name,int capacity){
        return new ArenaImpl(name,capacity);
    }

}
