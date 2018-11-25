package app.entities.persons;

import app.entities.Course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    private String email;
    private Double salaryPerHour;
    private Set<Course> courses;

    public Teacher() {
        setCourses(new HashSet<>());
    }

    public Teacher(String firstName, String lastName,
                   String phoneNumber, String email,
                   Double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        setEmail(email);
        setSalaryPerHour(salaryPerHour);
        setCourses(new HashSet<>());
    }

    @Column(name = "email")
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salary_per_hour")
    @Min(value = 0)
    public Double getSalaryPerHour() {
        return this.salaryPerHour;
    }

    public void setSalaryPerHour(Double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @OneToMany(targetEntity = Course.class, mappedBy = "teacher")
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
