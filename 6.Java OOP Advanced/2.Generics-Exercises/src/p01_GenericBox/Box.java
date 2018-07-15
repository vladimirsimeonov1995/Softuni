package p01_GenericBox;

public class Box<T extends Comparable> {

    private T item;

    public Box(T item){
        this.item = item;
    }

    public T getItem() {
        return this.item;
    }

    @Override
    public String toString(){

        String result = String.format("%s: %s",item.getClass(),item);

        return result.substring(6,result.length());

    }

}
