package contracts;

public interface IBoat extends IModelable {

    String getModel();

    int getWeight();

    double CalculateRaceSpeed(IRace race);

    boolean isItMotorBoat();
}
