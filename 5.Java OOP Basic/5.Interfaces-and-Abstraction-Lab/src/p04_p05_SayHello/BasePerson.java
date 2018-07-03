package p04_p05_SayHello;


public abstract class BasePerson implements Person {

    private String name;

    protected BasePerson(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
