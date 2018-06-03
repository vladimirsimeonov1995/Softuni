import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class TransportPrice {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int distance = Integer.parseInt(input.nextLine());
        String dayOrNight = input.nextLine();

        double price = 0;

        if(distance<20 && dayOrNight.equals("day"))
            price = distance*0.79 + 0.7;
        else if(distance<20 && dayOrNight.equals("night"))
            price = distance*0.9 + 0.7;
        else if(distance >= 20 && distance < 100)
            price = distance*0.09 ;
        else if(distance >= 100 )
            price = distance*0.06;

        System.out.printf("%.2f",price);


    }
}


