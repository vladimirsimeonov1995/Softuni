package contracts;

public interface IEngine extends IModelable{
    String getModel();

    int getHorsepower();

    int getDisplacement();

    int getOutput();
}
