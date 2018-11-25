package app.entities.persons;

import app.entities.Course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {

    private Double averageGrade;
    private Double attendance;
    private Set<Course> courses;

    public Student() {
        setCourses(new HashSet<>());
    }

    public Student(String firstName, String lastName,
                   String phoneNumber, Double averageGrade,
                   Double attendance) {
        super(firstName, lastName, phoneNumber);
        setAverageGrade(averageGrade);
        setAttendance(attendance);
        setCourses(new HashSet<>());
    }

    @Column(name = "average_grade")
    @Min(value = 2)
    public Double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")
    @Min(value = 0)
    public Double getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    @ManyToMany(mappedBy = "students", targetEntity = Course.class)
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
