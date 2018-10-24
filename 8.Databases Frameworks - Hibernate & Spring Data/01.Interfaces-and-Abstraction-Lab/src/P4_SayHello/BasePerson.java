package P4_SayHello;

public class BasePerson implements Person {

    private String name;

    public BasePerson(String name){
        this.setName(name);
    }

    private void setName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sayHello() {
        System.out.println("Hello");
    }
}
