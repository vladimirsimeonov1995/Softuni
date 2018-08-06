package enumeration;

public enum  Places {

    FIRST(1), SECOND(2), THIRD(3);

    private int number;

    Places(int number){
        this.number = number;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
