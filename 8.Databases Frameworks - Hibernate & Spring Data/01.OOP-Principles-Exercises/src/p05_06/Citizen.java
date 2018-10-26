package p05_06;

public class Citizen implements Identificationable, Borned {

    private String name;
    private Integer age;
    private String id;
    private String birthdate;

    public Citizen(String name, Integer age, String id, String birthdate){
        this.setName(name);
        this.setAge(age);
        this.setId(id);
        this.setBirthdate(birthdate);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(Integer age) {
        this.age = age;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }
}
