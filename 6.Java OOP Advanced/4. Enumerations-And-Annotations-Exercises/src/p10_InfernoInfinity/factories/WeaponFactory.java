package p10_InfernoInfinity.factories;

import p10_InfernoInfinity.entities.weapons.Axe;
import p10_InfernoInfinity.entities.weapons.Knife;
import p10_InfernoInfinity.entities.weapons.Sword;
import p10_InfernoInfinity.entities.weapons.Weapon;

public class WeaponFactory {

    public WeaponFactory(){}

    public Weapon createWeapon(String type, String name){

        switch (type){

            case "AXE":
                return new Axe(name);

            case "KNIFE":
                return new Knife(name);

            case "SWORD":
                return new Sword(name);

                default: return null;

        }

    }

}
