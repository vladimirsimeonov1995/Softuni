
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MatchFullName {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]+$");

        Matcher matcher = pattern.matcher("");

        while (true){

            String line = input.nextLine();
            if("end".equals(line)){
                break;
            }

            matcher = pattern.matcher(line);

            while (matcher.find()){
                System.out.println(matcher.group(0));
            }


        }


    }
}