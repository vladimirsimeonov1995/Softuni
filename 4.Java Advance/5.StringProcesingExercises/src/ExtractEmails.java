import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmails {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String userName = "(^|\\s)[\\dA-Za-z][\\w\\-.]+?[\\dA-Za-z]+";
        String host = "[A-Za-z][A-Za-z-.]*?(\\.[A-Za-z-.]*?[A-Za-z]+)+";

        String line = input.readLine();

        Matcher matcher = Pattern.compile(userName + "@" + host).matcher(line);

        while (matcher.find()){
            System.out.println(matcher.group(0).trim());
        }



    }
}