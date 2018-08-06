package contracts;

import Core.Engine;
import database.BoatSimulatorDatabase;
import exeptions.*;

import java.util.List;

public interface IBoatSimulatorController {

    IRace getCurrentRace();

    BoatSimulatorDatabase getDatabase();


    String CreateBoatEngine(List<String> parameters) throws DuplicateModelException;

    String CreateRowBoat(List<String> parameters) throws DuplicateModelException;

    String CreateSailBoat(List<String> parameters) throws DuplicateModelException;

    String CreatePowerBoat(List<String> parameters) throws NonExistantModelException, DuplicateModelException;

    String CreateYacht(List<String> parameters)
            throws NonExistantModelException, DuplicateModelException;

    String OpenRace(List<String> parameters) throws RaceAlreadyExistsException;

    String SignUpBoat(List<String> parameters)
            throws NonExistantModelException, DuplicateModelException, NoSetRaceException;

    String StartRace(List<String> parameters)
            throws InsufficientContestantsException, NoSetRaceException;
}
