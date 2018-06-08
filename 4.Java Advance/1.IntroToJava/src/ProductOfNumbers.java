import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class ProductOfNumbers {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int firstNumber = input.nextInt();
        int secondNumber = input.nextInt();
        BigInteger result = new BigInteger("1");

        for (int i = firstNumber; i <= secondNumber ; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        System.out.printf("product[%d..%d] = %s",firstNumber,secondNumber,result);
    }
}


