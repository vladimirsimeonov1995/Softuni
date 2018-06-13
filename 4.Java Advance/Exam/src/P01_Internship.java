import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P01_Internship {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");

        int numberOfTask = Integer.parseInt(reader.readLine());
        int numberOfCandidates = Integer.parseInt(reader.readLine());

        ArrayDeque<String> stackOfTask = new ArrayDeque<>();
        ArrayDeque<String> queueOfCandidates = new ArrayDeque<>();

        for (int i = 0; i < numberOfTask; i++) {
            stackOfTask.push(reader.readLine());
        }

        for (int i = 0; i < numberOfCandidates; i++) {
            String name = reader.readLine();
            if(isNameValid(name,pattern)) {
                queueOfCandidates.add(name);
            }
        }



        while (queueOfCandidates.size() > 1 && stackOfTask.size() > 0){

            String candidate = queueOfCandidates.poll();
            String task = stackOfTask.pop();

            if(getAsciiValue(candidate) > getAsciiValue(task)){
                System.out.printf("%s solved %s.\n",candidate,task);
                queueOfCandidates.add(candidate);
            }
            else {
                System.out.printf("%s failed %s.\n",candidate,task);
                stackOfTask.addLast(task);
            }
        }

        if(queueOfCandidates.size() == 1){
            System.out.println(queueOfCandidates.poll() + " gets the job!");
        }
        else if(queueOfCandidates.size() > 1) {
            System.out.println(String.join(", ",queueOfCandidates));
        }





    }

    public static int getAsciiValue(String word){
        int sum = 0;


        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i);
        }

        return sum;
    }

    public static boolean isNameValid(String name,Pattern pattern){

        Matcher matcher = pattern.matcher(name);

        return  matcher.find();

    }
}

