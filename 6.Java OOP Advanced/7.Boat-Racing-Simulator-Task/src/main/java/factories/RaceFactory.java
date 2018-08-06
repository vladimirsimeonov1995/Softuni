package factories;

import contracts.IRace;
import models.races.Race;

public class RaceFactory {

    public static IRace createRace(int distance,int windSpeed,int oceanCurrentSpeed,Boolean allowsMotorboats){

        return new Race(distance,windSpeed,oceanCurrentSpeed,allowsMotorboats);

    }

}
