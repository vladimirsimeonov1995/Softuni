package p06_EqualityLogic;

public class Person implements Comparable {

    private String name;
    private int age;

    public Person(String name,int age){
        this.setName(name);
        this.setAge(age);
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

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * result + name.hashCode();
        result += 31 * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;

        return person.getName().equals(this.getName()) && person.getAge() == person.getAge();
    }

    @Override
    public int compareTo(Object o) {
        Person person = (Person) o;

       int compareName = person.getName().compareTo(this.getName()) ;
       if(compareName == 0)
           return Integer.compare(person.getAge(),this.getAge());

       return compareName;
    }
}
