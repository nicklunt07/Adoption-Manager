
import java.util.*;

public abstract class Person {
    private String name;
    private int age;
    private String gender;

    public Person(String name, int age, String gender) {
        this.name = name; 
        this.age = age; 
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return name + ":\tage: " + age + "\tgender:" + gender;
    }

    
    
    /*public void addPerson(Person person) {
      persons.add(person);
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }*/

    abstract int getID();


    
}
