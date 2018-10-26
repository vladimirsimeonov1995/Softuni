package p07;

public class Worker extends Human {

    private Double weekSalary;
    private Double workHoursPerDay;

    public Worker(String firstName, String lastName, Double weekSalary, Double workHoursPerDay) {
        super(firstName, lastName);
        this.setLastName(lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkHoursPerDay(workHoursPerDay);
    }

    private void setLastName(String lastName){

        if(lastName.length() <= 3){
            throw new
                    IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }

        //Last name set in super matching constructor!
        //Just check it here if its < = 3

    }

    private void setWeekSalary(Double weekSalary) {

        if(weekSalary < 10){
            throw new
                    IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }

        this.weekSalary = weekSalary;
    }

    private void setWorkHoursPerDay(Double workHoursPerDay) {

        if(workHoursPerDay < 1 || workHoursPerDay > 12){
            throw new
                    IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }

        this.workHoursPerDay = workHoursPerDay;
    }

    private Double calculatePayPerHour(){

        return (this.weekSalary / (this.workHoursPerDay * 7));

    }

    @Override
    public String toString() {
        return String.format("%s" +
                "Week Salary: %.2f\n" +
                "Hours per day: %.2f\n" +
                "Salary per hour: %.2f\n",
                super.toString(),
                this.weekSalary,
                this.workHoursPerDay,
                this.calculatePayPerHour());
    }
}
