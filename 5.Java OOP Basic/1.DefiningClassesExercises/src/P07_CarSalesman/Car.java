package P07_CarSalesman;

public class Car {

    private String model;
    private Engine engine;
    private int weight;
    private String color;

    public Car(String model, Engine engine) {
        this(model, engine, -1, "n/a");
    }

    public Car(String model, Engine engine, int weight) {
        this(model, engine, weight, "n/a");
    }

    public Car(String model, Engine engine, String color) {
        this(model, engine, -1, color);
    }

    public Car(String model, Engine engine, int weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getWeight() {
        if (weight == -1)
            return "n/a";
        else
            return weight + "";
    }

    public String getColor() {
        return color;
    }

    public String toSting(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getModel()).append(":\n")
                .append(this.getEngine().getModel())
                .append(":\nPower: ").append(this.getEngine().getPower())
                .append("\nDisplacement: ").append(this.getEngine().getDisplacement())
                .append("\nEfficiency: ").append(this.getEngine().getEfficiency())
                .append("\nWeight: ").append(this.getWeight())
                .append("\nColor: ").append(this.getColor())
                .append("\n");
        return sb.toString();
    }
}
