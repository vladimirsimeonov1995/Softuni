package p06_Animals;

public class Dog extends Animal {

    public Dog(String name,String age,String gender){
        super(name,age,gender);
    }

    @Override
    public String produceSound() {
        return "BauBau";
    }

}
