package controllers;

import Utility.Constants;
import contracts.*;
import database.BoatSimulatorDatabase;
import enumeration.Places;
import exeptions.*;
import factories.BoatFactory;
import factories.EngineFactory;
import factories.RaceFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BoatSimulatorController implements IBoatSimulatorController {
    private Map<Double, IBoat> map;
    private BoatSimulatorDatabase database;
    private IRace currentRace;

    public BoatSimulatorController() {
        this.setDatabase(new BoatSimulatorDatabase());
        this.setCurrentRace(null);
        this.map = new LinkedHashMap<>();
    }

    public BoatSimulatorDatabase getDatabase() {
        return this.database;
    }

    public void setDatabase(BoatSimulatorDatabase database) {
        this.database = database;
    }

    public IRace getCurrentRace() {
        return this.currentRace;
    }

    public void setCurrentRace(IRace currentRace) {
        this.currentRace = currentRace;
    }

    @Override
    public String CreateBoatEngine(List<String> parameters) throws DuplicateModelException {

        String model = parameters.get(0);
        int horsepower = Integer.parseInt(parameters.get(1));
        int displacement = Integer.parseInt(parameters.get(2));
        String engineType = parameters.get(3);

        //String model, int horsepower, int displacement, EngineType engineType

        IEngine engine = null;

        try {
            engine = EngineFactory.createEngine(model, horsepower, displacement, engineType);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ignore) {
            throw new IllegalArgumentException(Constants.IncorrectEngineTypeMessage);
        }


        this.database.getEngines().Add(engine);
        return String.format(
                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model,
                horsepower,
                displacement);

    }

    @Override
    public String CreateRowBoat(List<String> parameters) throws DuplicateModelException {
        //String model, int weight, int oars

        String model = parameters.get(0);
        int weight = Integer.parseInt(parameters.get(1));
        int oars = Integer.parseInt(parameters.get(2));

        IBoat boat = BoatFactory.createRowBoat(model, weight, oars);

        this.database.getBoats().Add(boat);

        return String.format("Row boat with model %s registered successfully.", model);
    }

    @Override
    public String CreateSailBoat(List<String> parameters) throws DuplicateModelException {

        String model = parameters.get(0);
        int weight = Integer.parseInt(parameters.get(1));
        int sailEfficiency = Integer.parseInt(parameters.get(2));

        IBoat boat = BoatFactory.createSailBoat(model, weight, sailEfficiency);

        this.database.getBoats().Add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    @Override
    public String CreatePowerBoat(List<String> parameters) throws NonExistantModelException, DuplicateModelException {

        String model = parameters.get(0);
        int weight = Integer.parseInt(parameters.get(1));
        String firstEngineModel = parameters.get(2);
        String secondEngineModel = parameters.get(3);

        IEngine firstEngine = (IEngine) this.database.getEngines().GetItem(firstEngineModel);

        IEngine secondEngine = (IEngine) this.database.getEngines().GetItem(secondEngineModel);

        List<IEngine> engines = new ArrayList<IEngine>() {{
            add(firstEngine);
            add(secondEngine);
        }};

        IBoat boat = BoatFactory.createPowerBoat(model, weight, engines);

        this.database.getBoats().Add(boat);
        return String.format("Power boat with model %s registered successfully.", model);

    }

    @Override
    public String CreateYacht(List<String> parameters)
            throws NonExistantModelException, DuplicateModelException {

        //model, weight,  engine, cargoWeight

        String model = parameters.get(0);
        int weight = Integer.parseInt(parameters.get(1));
        String engineModel = parameters.get(2);
        int cargoWeight = Integer.parseInt(parameters.get(3));

        IEngine engine = (IEngine) this.database.getEngines().GetItem(engineModel);

        IBoat boat = BoatFactory.createYachtBoat(model, weight, engine, cargoWeight);

        this.database.getBoats().Add(boat);

        return String.format("Yacht with model %s registered successfully.", model);

    }

    @Override
    public String OpenRace(List<String> parameters) throws RaceAlreadyExistsException {
        //int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats

        int distance = Integer.parseInt(parameters.get(0));
        int windSpeed = Integer.parseInt(parameters.get(1));
        int oceanCurrentSpeed = Integer.parseInt(parameters.get(2));
        Boolean allowsMotorboats = Boolean.valueOf(parameters.get(3));


        IRace race = RaceFactory.createRace(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        this.ValidateRaceIsEmpty();
        this.currentRace = race;

        return
                String.format(
                        "A new race with distance %s meters, wind speed %s m/s and ocean current speed %s m/s has been set.",
                        distance, windSpeed, oceanCurrentSpeed);
    }

    private void ValidateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RaceAlreadyExistsMessage);
        }
    }

    @Override
    public String SignUpBoat(List<String> parameters)
            throws NonExistantModelException, DuplicateModelException, NoSetRaceException {

        String model = parameters.get(0);

        IBoat boat = this.database.getBoats().GetItem(model);

        this.ValidateRaceIsSet();

        if (!this.currentRace.getAllowsMotorboats() && boat.isItMotorBoat()) {
            throw new IllegalArgumentException(Constants.IncorrectBoatTypeMessage);
        }

        this.currentRace.AddParticipant(boat);

        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    private void ValidateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }
    }


    @Override
    public String StartRace(List<String> parameters)
            throws InsufficientContestantsException, NoSetRaceException {

        this.ValidateRaceIsSet();

        List<IBoat> participants = this.currentRace.GetParticipants();

        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.InsufficientContestantsMessage);
        }


        for (int i = 0; i < 3; i++) {
            FindFastest(participants);
        }

        if(map.size() < Constants.MinNumberOfParticipants){

            for (int i = 0; i < Constants.MinNumberOfParticipants - map.size() ; i++) {
                map.put(Constants.NegativeTime, participants.get(0));
                participants.remove(participants.get(0));
            }

        }


        int i = 0;

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Double, IBoat> doubleMotorBoatEntry : map.entrySet()) {

            if(i != Constants.MinNumberOfParticipants -1 ) {
                result.append(String.format("%s place: %s Model: %s Time: %s%n",
                        Places.values()[i].toString(),
                        doubleMotorBoatEntry.getValue().getClass().getSimpleName(),
                        doubleMotorBoatEntry.getValue().getModel(),
                        isFinished(doubleMotorBoatEntry.getKey())));
            }else {
                result.append(String.format("%s place: %s Model: %s Time: %s",
                        Places.values()[i].toString(),
                        doubleMotorBoatEntry.getValue().getClass().getSimpleName(),
                        doubleMotorBoatEntry.getValue().getModel(),
                        isFinished(doubleMotorBoatEntry.getKey())));
            }

            i++;
        }

        this.currentRace = null;

        return result.toString();
    }

    private void FindFastest(List<IBoat> participants) {

        Double bestTime = Double.MAX_VALUE;
        IBoat winner = null;

        for (IBoat participant : participants) {

            Double speed = participant.CalculateRaceSpeed(this.currentRace);
            Double time = this.currentRace.getDistance() / speed;

            if (time < bestTime && time > 0) {
                bestTime = time;
                winner = participant;
            }
        }

        if(winner != null) {
            if(map.containsKey(bestTime)){
                bestTime = bestTime - 0.0001;
            }

            map.put(bestTime, winner);
            participants.remove(winner);
        }


    }

    private String isFinished(Double key) {

        //key == Double.NEGATIVE_INFINITY

        if (key < Constants.THE_NUMBER_ZERO) {
            return "Did not finish!";
        }

        return String.format("%.2f sec", key);
    }

}
