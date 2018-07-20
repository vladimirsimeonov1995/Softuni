package p04_ComparingObjects;

public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.setName(name);
        this.setAge(age);
        this.setTown(town);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public String getTown() {
        return this.town;
    }

    private void setTown(String town) {
        this.town = town;
    }


    @Override
    public int compareTo(Person next) {
        int nameCompare = this.getName().compareTo(next.getName());

        if(nameCompare == 0){

            int ageCompare = Integer.compare(this.getAge(),next.getAge());
            if(ageCompare == 0){
                return this.getTown().compareTo(next.getTown());
            }

            return ageCompare;
        }

        return nameCompare;
    }
}
