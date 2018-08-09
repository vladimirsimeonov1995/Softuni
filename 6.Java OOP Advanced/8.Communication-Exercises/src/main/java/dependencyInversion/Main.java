package dependencyInversion;

import dependencyInversion.constants.Constants;
import dependencyInversion.interfaces.Strategy;
import dependencyInversion.models.PrimitiveCalculator;
import dependencyInversion.models.strategies.AdditionStrategy;
import dependencyInversion.models.strategies.DividingStrategy;
import dependencyInversion.models.strategies.MultiplyingStrategy;
import dependencyInversion.models.strategies.SubtractionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PrimitiveCalculator calculator = new PrimitiveCalculator();

        whileCicle(reader, calculator);


    }

    private static void whileCicle(BufferedReader reader, PrimitiveCalculator calculator) throws IOException {
        while (true){

            String[] command = reader.readLine().split(Constants.SPLIT_BY_SPACE);

            if(Constants.TERMINATE_COMMAND.equals(command[0]))
                break;

            if(Constants.MODE_COMMAND.equals(command[0])){
                Strategy strategy = getStrategy(command[1].charAt(0));
                calculator.changeStrategy(strategy);
            }else {

                int firstOperand = Integer.parseInt(command[0]);
                int secondOperand = Integer.parseInt(command[1]);

                System.out.println(calculator.performCalculation(firstOperand, secondOperand));
            }

        }
    }

    private static Strategy getStrategy(char operand){

        switch (operand){

            case '+':
                return new AdditionStrategy();
            case '-':
                return new SubtractionStrategy();
            case '/':
                return new DividingStrategy();
            case '*':
                return new MultiplyingStrategy();
                default: return null;

        }

    }

}
