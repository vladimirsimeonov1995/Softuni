import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class ExtractBitFromInt {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int p = input.nextInt();

        int mask = n >> p;         // 00000000 00001001
        int bit = mask & 1;        // 00000000 00000001

        System.out.println(bit);   // 1

    }
}


