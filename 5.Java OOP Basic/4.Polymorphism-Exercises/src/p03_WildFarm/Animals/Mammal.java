package p03_WildFarm.Animals;

public abstract class Mammal extends Animal {

    private String livingRegion;

    protected Mammal(String animalName, String animalType,
                     Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    protected String getLivingRegion() {
        return this.livingRegion;
    }

    protected void setLivingRegion(String livingRegion) {
        this.livingRegion = livingRegion;
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]",
                super.getAnimalType(), super.getAnimalName(),
                formatter.format(this.getAnimalWeight()),
                this.getLivingRegion(),
                super.getFoodEaten());
    }
}
