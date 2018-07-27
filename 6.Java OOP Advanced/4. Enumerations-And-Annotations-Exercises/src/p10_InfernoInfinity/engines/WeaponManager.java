package p10_InfernoInfinity.engines;

import p10_InfernoInfinity.entities.gems.Gem;
import p10_InfernoInfinity.entities.weapons.Weapon;
import p10_InfernoInfinity.factories.GemFactory;
import p10_InfernoInfinity.factories.WeaponFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WeaponManager {

    private Map<String,Weapon> weapons;
    private WeaponFactory weaponFactory;
    private GemFactory gemFactory;

    public WeaponManager(){
        this.weaponFactory = new WeaponFactory();
        this.gemFactory = new GemFactory();
        this.weapons = new LinkedHashMap<>();
    }

    public void create(String... args){

        String type = args[0];
        String name = args[1];

        Weapon newWeapon = this.weaponFactory.createWeapon(type,name);

        if(newWeapon != null)
            this.weapons.put(name,newWeapon);
    }

    public void addGemToWep(String... args){

        String weaponName = args[0];
        int socet = Integer.parseInt(args[1]);
        String gemType = args[2];

        Gem gem = this.gemFactory.createGem(gemType);

        if(gem != null)
            this.weapons.get(weaponName).addGem(gem,socet);


    }

    public void removeGemFromWep(String... args){

        String weaponName = args[0];
        int position = Integer.parseInt(args[1]);

        this.weapons.get(weaponName).removeGem(position);

    }

    public String print(){

        StringBuilder sb = new StringBuilder();

        for (Weapon weapon : weapons.values()) {
            sb.append(weapon.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String compare(String firstName, String secondName){

        Weapon firstWeapon = this.weapons.get(firstName);
        Weapon secondWeapon = this.weapons.get(secondName);

        if(firstWeapon.compareTo(secondWeapon) >= 0)
            return firstWeapon.toString();

        return secondWeapon.toString();

    }



}
