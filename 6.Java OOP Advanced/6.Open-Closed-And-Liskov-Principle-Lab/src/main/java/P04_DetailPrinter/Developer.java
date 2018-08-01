package P04_DetailPrinter;

public class Developer extends Employee {

    private String project;

    public Developer(String name, String project) {
        super(name);
        this.project = project;
    }

    @Override
    public String toString() {
        return String.format("I am %s and i'm working on %s project",super.getName(),this.project);
    }
}
