import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LettersChangeNumbers {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] inputStrings = input.nextLine().split("[\\s]+");

        if(inputStrings.length == 0)
            return;

        double totalSum = 0;




        for (String inputString : inputStrings) {
            totalSum += getSum(inputString);
        }

        System.out.printf("%.2f",totalSum);

    }

    public static double getSum(String word){

        String alphabet = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        double sum = 0;

        int number = Integer.parseInt(word.substring(1,word.length()-1));

        //First Letter
        if(Character.isUpperCase(word.charAt(0))){
            sum = (double)number / (double)alphabet.indexOf(word.charAt(0));
        }
        else {
            char letter = word.toUpperCase().charAt(0);
            sum = number * alphabet.indexOf(letter);
        }

        //LastLetter
        if(Character.isUpperCase(word.charAt(word.length()-1))){
            sum -= alphabet.indexOf(word.charAt(word.length()-1));
        }
        else {
            char letter = word.toUpperCase().charAt(word.length()-1);
            sum += alphabet.indexOf(letter);
        }


        return sum;

    }
}