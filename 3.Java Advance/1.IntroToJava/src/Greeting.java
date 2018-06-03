import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Greeting {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstName = input.nextLine();
        String secondName = input.nextLine();
        firstName = checkName(firstName);
        secondName = checkName(secondName);

        System.out.printf("Hello, %s %s!",firstName,secondName);
    }

    private static String checkName(String name){
        if(name.isEmpty()){
            name = "*****";
        }
        return name;
    }
}

