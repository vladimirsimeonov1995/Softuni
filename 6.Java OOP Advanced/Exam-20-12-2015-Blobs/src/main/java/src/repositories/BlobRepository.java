package src.repositories;

import src.factories.AttackFactory;
import src.factories.BehaviorFactory;
import src.factories.BlobFactory;
import src.interfaces.Attack;
import src.interfaces.Behavior;
import src.models.Blob;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlobRepository {

    private Map<String , Blob> blobs;

    private BlobFactory blobFactory;
    private BehaviorFactory behaviorFactory;
    private AttackFactory attackFactory;


    public BlobRepository(){
        this.blobs = new LinkedHashMap<String, Blob>();
        this.blobFactory = new BlobFactory();
        this.behaviorFactory = new BehaviorFactory();
        this.attackFactory = new AttackFactory();
    }


    public String create(String[] args){

        String name = args[1];
        String health = args[2];
        String damage = args[3];
        String behaviorName = args[4];
        String attackName = args[5];

        Behavior behavior = this.behaviorFactory.create(behaviorName);
        Attack attack = this.attackFactory.create(attackName);

        blobs.put(name,this.blobFactory.create(name,health,damage,behavior,attack));

        this.makeTurnOnBlobs();

        return null;
    }

    public String attack(String[] args){

        String attackerName = args[1];
        String targetName = args[2];

        this.blobs.get(attackerName).attack(this.blobs.get(targetName));

        this.makeTurnOnBlobs();

        return null;
    }

    public String pass(String[] args){
        this.makeTurnOnBlobs();

        return null;
    }

    public String status(String[] args){


        StringBuilder sb = new StringBuilder();

        for (Blob blob : blobs.values()) {
            sb.append(blob);
        }

        this.makeTurnOnBlobs();

        return sb.toString();

    }



    private void makeTurnOnBlobs(){
        for (Blob blob : blobs.values()) {
            blob.makeTurn();
        }
    }






}
