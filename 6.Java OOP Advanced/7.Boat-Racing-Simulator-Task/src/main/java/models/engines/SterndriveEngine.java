package models.engines;

public class SterndriveEngine extends BaseEngine {


    private static final int MULTIPLIER = 7;
    private static final int ZERO = 0;

    public int cachedOutput;

    public SterndriveEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
        this.cachedOutput = ZERO;
    }


    public int getOutput() {

        return super.getOutput(this.cachedOutput , MULTIPLIER);

    }



}
