package P04_DetailPrinter;

public class Manager extends Employee {

    private Iterable<String> documents;

    public Manager(String name, Iterable<String> documents) {
        super(name);
        this.documents = documents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("I am ").append(super.getName()).append(" and I am manager. My documents are:");

        for (String document : documents) {
            sb.append("\n - ").append(document);
        }

        return sb.toString();
    }
}
