import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsByGroup {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String lastName = line[1];
            int group = Integer.parseInt(line[2]);

            students.add(new Student(firstName,lastName,group));
        }

        students.stream()
                .filter(x -> x.getGroup() == 2)
                .sorted((a,b) -> a.getFirstName().compareTo(b.getFirstName()))
                .forEach(x -> System.out.println(x.getFirstName()+ " " + x.getLastName()));




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
