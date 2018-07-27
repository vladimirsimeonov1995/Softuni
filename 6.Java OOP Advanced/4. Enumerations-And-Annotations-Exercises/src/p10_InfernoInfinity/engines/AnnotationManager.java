package p10_InfernoInfinity.engines;

import p10_InfernoInfinity.anotations.WeaponAnotation;
import p10_InfernoInfinity.entities.weapons.Weapon;

public class AnnotationManager {

    public AnnotationManager(){}

    public String author(){
        return String.format("Author: %s"
                ,Weapon.class.getAnnotation(WeaponAnotation.class).author());
    }

    public String revision(){
        return String.format("Revision: %s"
                ,Weapon.class.getAnnotation(WeaponAnotation.class).revision());
    }

    public String description(){
        return String.format("Class description: %s"
                , Weapon.class.getAnnotation(WeaponAnotation.class).description());
    }

    public String reviewers(){
        String[] array = Weapon.class.getAnnotation(WeaponAnotation.class).reviewers();

      return String.format("Reviewers: %s"
              ,String.join(", ",Weapon.class.getAnnotation(WeaponAnotation.class).reviewers()));
    }

}
