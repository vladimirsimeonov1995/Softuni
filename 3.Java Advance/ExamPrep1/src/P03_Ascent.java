import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P03_Ascent {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("([,|_])([a-zA-Z]+)([0-9])");

        Map<String,String> memorize = new LinkedHashMap<>();

        while (true){

            String line = reader.readLine();
            if("Ascend".equals(line))
                break;

            for (String s : memorize.keySet()) {
                line = line.replace(s,memorize.get(s));
            }

            Matcher matcher = pattern.matcher(line);

            while (matcher.find()){


                StringBuilder decode = new StringBuilder();
                String word = matcher.group(2);
                String command = matcher.group(1);
                int digit = Integer.parseInt(matcher.group(3));

                if(command.equals(",")) {
                    for (int i = 0; i < word.length(); i++) {
                        decode.append((char) (word.charAt(i) + digit));
                    }
                }
                else {
                    for (int i = 0; i < word.length(); i++) {
                        decode.append((char) (word.charAt(i) - digit));
                    }
                }

                memorize.put(matcher.group(0),decode.toString());
                line = line.replace(matcher.group(0),decode.toString());
            }

            System.out.println(line);
        }


    }

}
