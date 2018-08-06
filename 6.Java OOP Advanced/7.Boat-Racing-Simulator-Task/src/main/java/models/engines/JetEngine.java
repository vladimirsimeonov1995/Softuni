package models.engines;

public class JetEngine extends BaseEngine {

    private static final int MULTIPLIER = 5;
    private static final int ZERO = 0;

    public int cachedOutput;

    public JetEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);

        this.cachedOutput = ZERO;
    }

    public int getOutput() {

        return super.getOutput(this.cachedOutput , MULTIPLIER);

    }



}
