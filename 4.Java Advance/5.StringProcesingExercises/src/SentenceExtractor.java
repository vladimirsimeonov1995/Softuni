import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceExtractor {

    public static void main(String[] args)  {

        Scanner input = new Scanner(System.in);

        Pattern pattern = Pattern.compile("[^!?.]+[!.?]");

        String keyWord = input.nextLine().toLowerCase();
        String text = input.nextLine();

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            if(matcher.group(0).toLowerCase().contains(" "+keyWord+" ")){
                System.out.println(matcher.group(0).trim());
            }
            else if(matcher.group(0).toLowerCase().contains(keyWord+"!")){
                System.out.println(matcher.group(0).trim());
            }
            else if(matcher.group(0).toLowerCase().contains(keyWord+"?")){
                System.out.println(matcher.group(0).trim());
            }
            else if(matcher.group(0).toLowerCase().contains(keyWord+".")){
                System.out.println(matcher.group(0).trim());
            }
        }
    }
}