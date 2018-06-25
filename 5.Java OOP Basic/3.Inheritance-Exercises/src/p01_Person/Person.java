package p01_Person;

public class Person {

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age){
        this.setName(name);
        this.setAge(age);
    }

    protected void setName(String name){
        if(name.length() < 3)
            throw new IllegalArgumentException("Name's length should not be less than 3 symbols!");

        this.name = name;
    }

    protected void setAge(int age){
        if(age < 0 )
            throw new IllegalArgumentException("Age must be positive!");

        this.age = age;
    }

    @Override
    public String toString(){
        return String.format("Name: %s, Age: %d",this.name,this.age);
    }
}
