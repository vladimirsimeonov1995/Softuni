package src.interfaces;

import src.models.Blob;

public interface Attack {

    void execute(Blob attacker, Blob target);

}
