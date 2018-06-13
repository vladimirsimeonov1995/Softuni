import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_Ranking {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("[^:,=,>]+");

        Map<String,String> contests = new HashMap<>();

        Map<String ,Map<String ,Integer>> students = new TreeMap<>();

        getContest(reader, pattern, contests);

        getStudents(reader, contests, students);

        String name = null;
        int maxSum = 0;

        for (String student : students.keySet()) {
            int currentSum = 0;
            for (int points : students.get(student).values()) {
                currentSum += points;
            }
            if(currentSum > maxSum){
                maxSum = currentSum;
                name = student;
            }
        }


        System.out.printf("Best candidate is %s with total %d points.\nRanking: \n",name,maxSum);

        for (String student : students.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(student).append("\n");
            students.get(student).entrySet().stream()
                    .sorted((x,y) -> {
                        return y.getValue() - x.getValue();
                    })
                    .forEach(subject -> {
                        sb.append("#  ").append(subject.getKey()).append(" -> ")
                                .append(subject.getValue()).append("\n");
                    });
            System.out.print(sb);

        }





    }

    private static void getStudents(BufferedReader reader, Map<String, String> contests, Map<String, Map<String, Integer>> students) throws IOException {
        while (true){

            String[] line = reader.readLine().split("=>");
            if("end of submissions".equals(line[0]))
                break;

            if(contests.containsKey(line[0])){
                if(contests.get(line[0]).equals(line[1])){

                    int points = Integer.parseInt(line[3]);


                    if(!students.containsKey(line[2])){
                        Map<String ,Integer> grades = new LinkedHashMap<>();
                        grades.put(line[0],points);
                        students.put(line[2],grades);
                    }
                    else{
                        if(students.get(line[2]).containsKey(line[0])){
                            if(students.get(line[2]).get(line[0]) < points){
                                students.get(line[2]).put(line[0],points);
                            }
                        }
                        else {
                            students.get(line[2]).put(line[0],points);
                        }
                    }
                }
            }
        }
    }

    private static void getContest(BufferedReader reader, Pattern pattern, Map<String, String> contests) throws IOException {
        while (true){

            String[] line = reader.readLine().split(":");
            if("end of contests".equals(line[0]))
                break;
            String contest = null;
            String pass = null;

            Matcher matcher = pattern.matcher(line[0]);
            if(matcher.find()){
                contest = line[0];
                pass = line[1];
            }

            contests.put(contest,pass);
        }
    }

}
