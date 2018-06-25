package p06_Animals;

public class Animal {

    private String name;
    private int age;
    private String gender;

    public Animal(String name,String age,String gender){
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setAge(String age){
        if(!age.matches("\\d+") )
            throw new IllegalArgumentException("Invalid input!");

        int integerAge = Integer.parseInt(age);
        if(integerAge < 0)
            throw new IllegalArgumentException("Invalid input!");

        this.age = integerAge;
    }

    private void setGender(String gender){
        this.gender = gender;
    }

    public String produceSound(){
        return "Not implemented!";
    }

}
