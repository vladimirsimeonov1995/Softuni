package p06_BirthdayCelebration;

public class Citizen implements Enterable,Birthable {

    private String name;
    private Integer age;
    private String id;
    private String birthDate;

    public Citizen(String name, Integer age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }
}
