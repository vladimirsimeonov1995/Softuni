import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByGroup {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){

            String[] line = reader.readLine().split("\\s+");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String lastName = line[1];
            int group = Integer.parseInt(line[2]);

            students.add(new Student(firstName,lastName,group));

        }



        Map<Integer,List<Student>> groupedStudents = students.stream().collect(Collectors.groupingBy(Student::getGroup));

        groupedStudents.entrySet()
                .forEach(x -> {
                    StringBuilder sb = new StringBuilder().append(x.getKey()).append(" - ");
                    x.getValue().forEach(student -> {
                        sb.append(student.getFirstName()).append(" ").append(student.getLastName()).append(", ");
                    });
                    System.out.println(sb.toString().substring(0,sb.length()-2));
                });


    }

    public static class Student{

        String firstName;
        String lastName;
        int group;

        public Student() {
        }

        public Student(String firstName, String lastName, int group) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.group = group;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }
    }

}
