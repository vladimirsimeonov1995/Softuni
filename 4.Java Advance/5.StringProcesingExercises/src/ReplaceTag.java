import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReplaceTag {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("(<a)\\s+href[^>]+(>)\\s*.*?(</a>)");


        StringBuilder sb = new StringBuilder();
        String line;
        while (true) {
            if ("END".equals(line = input.readLine())) {
                break;
            }

            sb.append(line).append(System.lineSeparator());
        }

        String result = sb.toString();

        Matcher matcher = pattern.matcher(result);


        while (matcher.find()){

            String replacement = matcher.group(0);
            String openTag = matcher.group(1);
            String middleTag = matcher.group(2);
            String closeTag = matcher.group(3);

            replacement = replacement.replace(openTag,"[URL");
            replacement = replacement.replace(closeTag,"[/URL]");
            replacement = replacement.replace(middleTag,"]");


            result = result.replace(matcher.group(0),replacement);
        }

        System.out.println(result);


    }
}