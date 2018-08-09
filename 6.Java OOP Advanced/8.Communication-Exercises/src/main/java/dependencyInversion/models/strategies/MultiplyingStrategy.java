package dependencyInversion.models.strategies;

import dependencyInversion.interfaces.Strategy;

public class MultiplyingStrategy implements Strategy {
    @Override
    public int Calculate(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }
}
