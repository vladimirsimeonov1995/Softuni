package models;

public class Person {

    private long id;
    private String name;

    public Person(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public long getId() {
        return this.id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
