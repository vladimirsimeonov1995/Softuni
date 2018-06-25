package p03_Mankind;

public class Student extends Human {

    private String facNumber;

    public Student(String firstName, String lastName,String facNumber){
        super(firstName,lastName);
        this.setFacNumber(facNumber);
    }

    private void setFacNumber(String facNumber){
        if(facNumber.length() <5 || facNumber.length() > 10)
            throw new IllegalArgumentException("Invalid faculty number!");

        this.facNumber = facNumber;
    }

    private String getFacNumber() {
        return this.facNumber;
    }

    @Override
    public String toString(){
        return String.format("First Name: %s\n" +
                "Last Name: %s\n" +
                "Faculty number: %s",
                super.getFirstName(), super.getLastName(), this.getFacNumber());
    }

}
