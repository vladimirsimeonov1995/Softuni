import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FilterStudentsByPhone {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String phoneNumber = line[2];
            String lastName = line[1];


            students.add(new Student(firstName,lastName,phoneNumber));
        }


        students.stream()
                .filter(student -> {
                    return student.getPhoneNumber().startsWith("02") || student.getPhoneNumber().startsWith("+3592");
                })
                .forEach(student -> {
                    StringBuilder sb = new StringBuilder().append(student.getFirstName()).append(" ").append(student.getLastName());
                    System.out.println(sb);
                });




    }

    public static class Student{

        String firstName;
        String lastName;
        String phoneNumber;

        public Student() {
        }

        public Student(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = email;
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

}
