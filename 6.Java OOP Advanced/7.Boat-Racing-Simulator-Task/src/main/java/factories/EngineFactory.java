package factories;

import Core.Engine;
import contracts.IEngine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EngineFactory {

    private static final String ENGINE_PATH = "models.engines.";
    private static final String ENGINE_NAME_END = "Engine";

    public static IEngine createEngine(String model, int horsepower, int displacement, String engineType)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        Class engineClass = Class.forName(ENGINE_PATH + engineType + ENGINE_NAME_END);

        Constructor constructor = engineClass.getDeclaredConstructor(String.class,int.class,int.class);

        return (IEngine) constructor.newInstance(model,horsepower,displacement);
    }

}
