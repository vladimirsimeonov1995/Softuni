package workForce.repositories;

import workForce.Job;
import workForce.employees.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class Repository {

    private List<Job> jobs ;
    private Map<String ,Employee> employees;

    public Repository(){
        jobs = new ArrayList<>();
        this.employees = new LinkedHashMap<>();
    }

    public void addJob(Job job){
        this.jobs.add(job);
    }

    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(this.employees);
    }

    public void addEmployee(Employee employee){
        this.employees.put(employee.getName(),employee);
    }

    public void passWeek(){
        this.jobs.forEach(Job::update);

        this.jobs = this.jobs.stream().filter(f -> f.getHoursOfWorkNeeded() > 0).collect(Collectors.toList());
    }

    public void status(){
        jobs.forEach(System.out::println);
    }

}
