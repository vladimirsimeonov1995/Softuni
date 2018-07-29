package pr0304Barracks.core;

import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Runnable;
import pr0304Barracks.contracts.UnitFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private static final String COMMAND_PATH = "pr0304Barracks.core.commands.";
    private static final String COMMAND_END = "Command";

    private Repository repository;
    private UnitFactory unitFactory;
    private String[] data;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpredCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: refactor for problem 4
    private String interpredCommand(String[] data, String commandName) {

        this.data = data;

        try {
            Class currentCommandClass = Class.forName(this.COMMAND_PATH
                    + Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1) + this.COMMAND_END);

            Constructor<?> constructor = currentCommandClass
                    .getDeclaredConstructor();

            Executable command = (Executable) constructor.newInstance();

            this.injectDependences(command);

            String result = command.execute();

            return result;

        } catch (ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            return "Incorrect command";
        }
    }

    private <T> void injectDependences(T command) throws IllegalAccessException {

        Field[] commandFields = command.getClass().getDeclaredFields();
        Field[] dependencesFields = this.getClass().getDeclaredFields();

        for (Field commandField : commandFields) {

            commandField.setAccessible(true);

            if(commandField.isAnnotationPresent(Inject.class)){

                for (Field dependencesField : dependencesFields) {

                    dependencesField.setAccessible(true);

                    if(commandField.getType().getSimpleName().equals(dependencesField.getType().getSimpleName()) &&
                            commandField.getType().equals(dependencesField.getType())){

                        commandField.set(command,dependencesField.get(this));
                    }

                }

            }

        }

    }


}
