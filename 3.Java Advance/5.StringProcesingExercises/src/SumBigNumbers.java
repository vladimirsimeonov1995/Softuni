import java.math.BigInteger;
import java.util.*;


public class SumBigNumbers {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        BigInteger firstNumber  = new BigInteger(input.nextLine());
        BigInteger secondNumber = new BigInteger(input.nextLine());

        firstNumber = firstNumber.add(secondNumber);

        System.out.println(firstNumber);



    }


}