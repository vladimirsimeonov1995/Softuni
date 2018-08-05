package src.factories;

import src.interfaces.Attack;
import src.interfaces.Behavior;
import src.models.Blob;

public class BlobFactory {

    public Blob create(String name, String health, String damage, Behavior behavior, Attack attack){

        int blobHealth = Integer.parseInt(health);
        int blobDamage = Integer.parseInt(damage);

        return new Blob(name,blobHealth,blobDamage,behavior,attack);


    }

}
