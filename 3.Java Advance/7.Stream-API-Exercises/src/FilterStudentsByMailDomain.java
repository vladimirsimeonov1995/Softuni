import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FilterStudentsByMailDomain {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String lastName = line[1];
            String email = line[2];

            students.add(new Student(firstName,lastName,email));
        }


        students.stream()
                .filter(student -> {
                    String domain = student.getEmail().split("@")[1];
                    return "gmail.com".equals(domain);
                })
                .forEach(student -> {
                    StringBuilder sb = new StringBuilder().append(student.getFirstName()).append(" ").append(student.getLastName());
                    System.out.println(sb);
                });




    }

    public static class Student{

        String firstName;
        String lastName;
        String email;

        public Student() {
        }

        public Student(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
