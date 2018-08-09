package dependencyInversion.models.strategies;

import dependencyInversion.interfaces.Strategy;

public class AdditionStrategy implements Strategy {

    public int Calculate(int firstOperand, int secondOperand){
        return firstOperand + secondOperand;
    }
}
