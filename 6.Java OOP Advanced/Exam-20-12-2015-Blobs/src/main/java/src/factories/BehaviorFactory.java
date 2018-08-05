package src.factories;

import src.interfaces.Behavior;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BehaviorFactory {

    private static final String PAHT_TO_BEAHVIORS = "src.models.behavors.";

    public Behavior create(String name) {
        try {
            Class behaviorClass = Class.forName(PAHT_TO_BEAHVIORS + name);

            Constructor constructor = behaviorClass.getDeclaredConstructor();

            return (Behavior) constructor.newInstance();


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
