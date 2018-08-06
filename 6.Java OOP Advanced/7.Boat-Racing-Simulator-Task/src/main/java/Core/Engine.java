package Core;

import Utility.Constants;
import contracts.*;
import exeptions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Engine {

    private Reader reader;
    private Writer writer;
    private ICommandHandler commandHandler;

    public Engine(Reader reader, Writer writer, ICommandHandler commandHandler) {

        this.reader = reader;
        this.writer = writer;
        this.commandHandler = commandHandler;
    }

    public void run() {

        String line = reader.readLine();

        while (!Constants.ENIND_COMMAND.equals(line)) {


            String name;
            List<String> parameters;


            List<String> tokens = Arrays.asList(line.split("\\\\"));
            name = tokens.get(0);
            parameters = tokens.stream().skip(1).collect(Collectors.toList());


            String commandResult = null;
            try {
                commandResult = this.commandHandler.ExecuteCommand(name, parameters);
                writer.writeLine(commandResult);
            } catch (DuplicateModelException | NonExistantModelException |
                    RaceAlreadyExistsException | NoSetRaceException |
                    InsufficientContestantsException | InvocationTargetException |
                    NoSuchMethodException | IllegalAccessException e) {
                writer.writeLine(e.getCause().getMessage());
            }



            line = reader.readLine();
        }
    }
}

//    public static void main(String[] args) {
//        IBoatSimulatorController ctrl = new IBoatSimulatorController() {
//            @Override
//            public IRace getCurrentRace() {
//                return null;
//            }
//
//            @Override
//            public BoatSimulatorDatabase getDatabase() {
//                return null;
//            }
//
//            @Override
//            public String CreateBoatEngine(String model, int horsepower, int displacement, String engineType) {
//                return null;
//            }
//
//            @Override
//            public String CreateRowBoat(String model, int weight, int oars) throws DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String CreateSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String CreatePowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String CreateYacht(String model, int weight, String engineModel, int cargoWeight) throws
//                    NonExistantModelException, DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String OpenRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
//                return null;
//            }
//
//            @Override
//            public String SignUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
//                return null;
//            }
//
//            @Override
//            public String StartRace() throws InsufficientContestantsException, NoSetRaceException {
//                return null;
//            }
//
//            @Override
//            public String GetStatistic() {
//                return null;
//            }
//        };
//
//        CommandHandler commandHandler = new CommandHandler(ctrl);
//        Engine engine = new Engine();
//        engine.Run();
//    }
//}
