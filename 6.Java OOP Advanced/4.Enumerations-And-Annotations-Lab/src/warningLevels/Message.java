package warningLevels;

public class Message {

    private String message;
    private Importance importance;

    public Message(String message, String importance) {
        this.message = message;
        this.importance = Enum.valueOf(Importance.class, importance.toUpperCase());
    }

    public Importance getImportance() {
        return this.importance;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",importance.name(),message);
    }
}
