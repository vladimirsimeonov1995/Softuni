package p03_Mankind;

public class Worker extends Human {

    private static final double WORK_DAYS = 7.0;

    private double weekSalary;
    private double workHoursPerDay;
    private double salaryPerHour;

    public Worker(String firstName, String lastName, double weekSalary, double workHoursPerDay){
        super(firstName,lastName);
        this.setLastName(lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkHoursPerDay(workHoursPerDay);
        this.setSalaryPerHour();setSalaryPerHour();

    }

    @Override
    protected void setLastName(String lastName) {
        if(lastName.length() <= 3)
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");

        super.setLastName(lastName);
    }

    private void setWeekSalary(double weekSalary){
        if(weekSalary <= 10)
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");

        this.weekSalary = weekSalary;
    }

    private void setWorkHoursPerDay(double workHoursPerDay){
        if(workHoursPerDay < 1 || workHoursPerDay > 12)
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");

        this.workHoursPerDay = workHoursPerDay;
    }

    private void setSalaryPerHour(){
        salaryPerHour = weekSalary / WORK_DAYS / workHoursPerDay;
    }

    private double getWeekSalary() {
        return this.weekSalary;
    }

    private double getWorkHoursPerDay() {
        return this.workHoursPerDay;
    }

    private double getSalaryPerHour() {
        return this.salaryPerHour;
    }

    @Override
    public String toString() {
        return String.format("First Name: %s\n" +
                "Last Name: %s\n" +
                "Week Salary: %.2f\n" +
                "Hours per day: %.2f\n" +
                "Salary per hour: %.2f",
                super.getFirstName(),super.getLastName(),this.getWeekSalary()
                ,this.getWorkHoursPerDay(),this.getSalaryPerHour());
    }
}
