
import java.io.Serializable;

/**
 * This class models a Person that has certain attributes
 * 
 * @author Nick Lunt
 * @version April 23, 2023
 */
public abstract class Person implements Serializable {
    private String name;
    private int age;
    private String gender;

    /**
     * Constructor for Person
     * 
     * @param String name
     * @param int    age
     * @param String gender
     */
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Retruns the name
     * 
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name
     * 
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age
     * @return int age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the age
     * @param int age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the gender
     * @return gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Sets the Gender of the person
     * @param String gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /** 
     * Retruns a to String
     * @return toString: String
     */
    public String toString() {
        return name + "\tage: " + age + "\tgender: " + gender;
    }

    /**
     * Forces all of it's subclasses to have an ID
     * @return long ID
     */
    abstract long getID();

}
