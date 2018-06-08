import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StudentsJoinedToSpecialties {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();

        List<Student> students = new ArrayList<>();
        Map<String,List<String>> fnAndSpec = new HashMap<>();

        while (!"Students:".equals(line)){
            String[] splittedLine = line.split("\\s+");
            String spec = splittedLine[0] + " " + splittedLine[1];
            String fn = splittedLine[2];

            if(fnAndSpec.containsKey(fn)){
                fnAndSpec.get(fn).add(spec);
            }
            else {
                List<String> specialties = new ArrayList<>(){{ add(spec);}};
                fnAndSpec.put(fn,specialties);
            }

            line = reader.readLine();
        }

        while (true){

            line = reader.readLine();
            if("END".equals(line))
                break;
            String[] splittedLine = line.split("\\s+");

            String fn = splittedLine[0];
            String name = splittedLine[1] + " " + splittedLine[2];
            List<String> specialties = fnAndSpec.get(fn);

            students.add(new Student(name, fn, specialties));

        }



        students.stream()
                .filter(student -> student.getSpecialities()!=null)
                .sorted(Comparator.comparing(Student::getName))
                .forEach(student -> {
                    student.getSpecialities()
                            .stream()
                            .forEach(spec -> {
                                StringBuilder sb = new StringBuilder()
                                        .append(student.getName())
                                        .append(" ")
                                        .append(student.getFn())
                                        .append(" ")
                                        .append(spec);
                                System.out.println(sb);
                            });
                });




    }


    public static class Student{

        String name;
        String fn;
        List<String> specialities = new ArrayList<>();

        public Student(String name, String fn, List<String> specialities) {
            this.name = name;
            this.fn = fn;
            this.specialities = specialities;
        }

        public Student() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFn() {
            return fn;
        }

        public void setFn(String fn) {
            this.fn = fn;
        }

        public List<String> getSpecialities() {
            return specialities;
        }

        public void setSpecialities(List<String> specialities) {
            this.specialities = specialities;
        }
    }
}
