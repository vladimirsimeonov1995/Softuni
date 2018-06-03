import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Lottery {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for (int i = 1; i <= 10; i++) {
            for (int j = i; j <= 10; j++) {
                for (int k = j; k <= 10; k++) {
                    if(i != j && j != k && k !=i)
                        System.out.printf("%d %d %d\n",i,j,k);
                }
            }

        }
    }
}


