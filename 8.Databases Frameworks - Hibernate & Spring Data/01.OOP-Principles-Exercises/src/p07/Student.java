package p07;

public class Student extends Human {

    private Integer facNumber;

    protected Student(String firstName, String lastName, String facNumber) {
        super(firstName, lastName);
        this.setFacNumber(facNumber);
    }

    private void setFacNumber(String facNumber) {

        if(facNumber.length() < 5 || facNumber.length() > 10){
            throw new
                    IllegalArgumentException("Invalid faculty number!");
        }

        this.facNumber = Integer.valueOf(facNumber);
    }

    @Override
    public String toString() {
        return String.format("%s" +
                "Faculty number: %d\n",
                super.toString(),
                this.facNumber);
    }
}
