import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsByAge {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String lastName = line[1];
            int age = Integer.parseInt(line[2]);

            students.add(new Student(firstName,lastName,age));
        }

        students.stream()
                .filter(student -> student.getAge() >= 18 && student.getAge() <= 24)
                .forEach(student -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(student.getFirstName()).append(" ").append(student.getLastName())
                            .append(" ").append(student.getAge());
                    System.out.println(sb);

                });




    }

    public static class Student{

        String firstName;
        String lastName;
        int age;

        public Student(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
