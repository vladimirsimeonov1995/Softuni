import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class P03_Genome {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("([!@#$?a-z]+)=([0-9]+)--([0-9]+)<<([a-z]+)");
        Pattern insidePattern = Pattern.compile("[a-z]+");

        Map<String,Integer> organisms = new LinkedHashMap<>();



        while (true){
            String line = reader.readLine();
            if("Stop!".equals(line))
                break;

            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){
                StringBuilder sb = new StringBuilder();
                Matcher insideMather = insidePattern.matcher(matcher.group(1));
                while (insideMather.find()){
                    sb.append(insideMather.group());
                }

                if(sb.length() == Integer.parseInt(matcher.group(2))){

                    if(!organisms.containsKey(matcher.group(4))){
                        organisms.put(matcher.group(4),Integer.parseInt(matcher.group(3)));
                    }
                    else {
                        organisms.put(matcher.group(4),
                                Integer.parseInt(matcher.group(3)) + organisms.get(matcher.group(4)));
                    }
                }
            }
        }

        organisms.entrySet().stream()
                .sorted((a,b)->{
                    return b.getValue() - a.getValue();
                })
                .forEach(organism -> {
                    System.out.println(organism.getKey() + " has genome size of " + organism.getValue());
                });


    }


}
