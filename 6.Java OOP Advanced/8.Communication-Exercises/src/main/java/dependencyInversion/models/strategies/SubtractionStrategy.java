package dependencyInversion.models.strategies;

import dependencyInversion.interfaces.Strategy;

public class SubtractionStrategy implements Strategy {
    public int Calculate(int firstOperand, int secondOperand){
        return firstOperand - secondOperand;
    }
}
