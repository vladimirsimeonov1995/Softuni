package dependencyInversion.models;


import dependencyInversion.interfaces.Strategy;
import dependencyInversion.models.strategies.AdditionStrategy;

public class PrimitiveCalculator {

    private Strategy strategy;


    public PrimitiveCalculator(){
        this.strategy = new AdditionStrategy();
    }

    public void changeStrategy(Strategy strategy){

        this.strategy = strategy;

    }

    public int performCalculation(int firstOperand,int secondOperand){

        return strategy.Calculate(firstOperand,secondOperand);

    }
}
