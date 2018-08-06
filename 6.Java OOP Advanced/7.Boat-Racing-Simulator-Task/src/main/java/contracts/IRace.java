package contracts;

import exeptions.DuplicateModelException;

import java.util.List;
import java.util.Map;

public interface IRace
{
    int getDistance();


    int getWindSpeed();

    int getOceanCurrentSpeed();

    Boolean getAllowsMotorboats();

    Map<String, IBoat> getRegisteredBoats();

    void AddParticipant(IBoat boat) throws DuplicateModelException;

    List<IBoat> GetParticipants();
}
