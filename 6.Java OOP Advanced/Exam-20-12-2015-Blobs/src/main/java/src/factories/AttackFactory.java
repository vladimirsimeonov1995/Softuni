package src.factories;

import src.interfaces.Attack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AttackFactory {

    private static final String PATH_TO_ATACK = "src.models.attacks.";

    public Attack create(String name) {
        try {
            Class attackClass = Class.forName(PATH_TO_ATACK + name);

            Constructor constructor = attackClass.getDeclaredConstructor();

            return (Attack) constructor.newInstance();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }

}
