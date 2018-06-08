import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class EuroTrip {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double number = input.nextDouble();
        BigDecimal bigNumber =new BigDecimal(number*1.2);
        BigDecimal exchange = new BigDecimal("4210500000000");
        bigNumber = bigNumber.multiply(exchange);
        System.out.printf("%.2f marks",bigNumber);
    }
}