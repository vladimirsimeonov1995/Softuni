package p03_Tuple;

public class Tuple<T , G> {

    private T item1;
    private  G item2;

    public Tuple(){
    }

    public T getItem1() {
        return this.item1;
    }

    public void setItem1(T item1) {
        this.item1 = item1;
    }

    public G getItem2() {
        return this.item2;
    }

    public void setItem2(G item2) {
        this.item2 = item2;
    }

    //{item1} -> {item2}
    @Override
    public String toString() {
        return String.format("%s -> %s",this.getItem1(),this.getItem2());
    }
}
