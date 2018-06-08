import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SortStudents {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String lastName = line[1];

            students.add(new Student(firstName,lastName));
        }

        students.stream()
                .sorted((student1,student2) -> {
                    int first = student1.getLastName().compareTo(student2.getLastName());
                    if(first == 0){
                        return student2.getFirstName().compareTo(student1.getFirstName());
                    }
                    else
                        return first;
                })
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));




    }

    public static class Student{

        String firstName;
        String lastName;

        public Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Student() {
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


    }

}
