import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MatchPhoneNumber {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Pattern pattern = Pattern.compile("[+]359([ ||-])2(\\1)\\d{3}(\\1)\\d{4}$");

        Matcher matcher = pattern.matcher("");

        while (true){

            String line = input.nextLine();
            if("end".equals(line)){
                break;
            }

            matcher = pattern.matcher(line);

            if(matcher.find())
                System.out.println(line);
        }
    }
}