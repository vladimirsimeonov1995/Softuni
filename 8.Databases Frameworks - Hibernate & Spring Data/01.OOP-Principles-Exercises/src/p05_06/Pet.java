package p05_06;

public class Pet implements Borned {

    private String name;
    private String birthdate;

    public Pet(String name,String birthdate){
        this.setName(name);
        this.setBirthdate(birthdate);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }
}
