
/**
 * Employee class
 */
import java.util.HashMap;

import javafx.scene.control.Button;

/**
 * This class models an Employee
 * 
 * @author Adnan Khaleeli(interface), Sushanth Ambati
 * @version April 24, 2023
 */
public class Employee extends Person implements Taskable, Responsible {

    private Skillable skill;
    private String position;
    private static long employeeID = 300001;
    private long id;
    private int yearsOfExperience;
    private String levelOfEducation;
    private boolean isFelon;
    public static HashMap<String, Skillable> employeeSkills = new HashMap<>();

    /**
     * Creates an employee object
     * 
     * @param String           name
     * @param int              age
     * @param String           gender
     * @param String           position
     * @param orphanage
     * @param skill
     * @param yearOfExprience
     * @param levelOfEducation
     * @param isFelon
     */
    public Employee(String name, int age, String gender, String position, Orphanage orphanage, Skillable skill,
            int yearOfExprience, String levelOfEducation, boolean isFelon) {
        super(name, age, gender);
        this.id = employeeID++;
        this.position = position;
        this.skill = skill;
        this.levelOfEducation = levelOfEducation;
        this.yearsOfExperience = yearOfExprience;
        orphanage.addPerson(this);
    }

    /**
     * Set skill
     * 
     * @param skill
     */
    public void setSkill(Skillable skill) {
        this.skill = skill;
    }

    /**
     * set the position of employee
     * 
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Adds to the employee skill Hashmap
     * 
     * @param key
     * @param skill
     */
    public static void addEmployeeSkillable(String key, Skillable skill) {
        employeeSkills.put(key, skill);
    }

    /*
     * 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @author Sushanth Ambati
     * @return position
     */
    protected String getPosition() {
        return this.position;
    }

    /**
     * Returns the ID of employee
     * 
     * @return long id
     */
    protected long getID() {

        return id;
    }

    /**
     * returns years of experience
     * 
     * @return int yearsOfExperience
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Retruns level of education
     * 
     * @return String levelOfEducation
     */
    public String getlevelOfEducation() {
        return levelOfEducation;
    }

    /**
     * Returns isFelon
     * 
     * @return boolean
     */
    public boolean getIsFelon() {
        return isFelon;
    }

    @Override
    /**
     * Returns String of object
     * @return String
     */
    public String toString() {
        return super.toString() + "\tID: " + getID() + "\tPosition: " + getPosition();
    }

    /**
     * Assigns Tasks
     * @return Skillable skill
     */
    public void assignTask(Skillable skill) {
        this.skill = skill;
    }

    /**
     * Returns Skillable 
     * @return Skillable skill
     */
    public Skillable getSkill() {
        return skill;
    }

    /**
     * Performs the tasks of the employee
     * @return Button
     */
    public Button performTask() {
        return skill.skill();
    }

    /**
     * Returns criminal record
     * @return String
     */
    public String getCriminalRecord() {
        return "This: " + getName() + " is a felon";
    }

    /**
     * Returns if the employee is valid
     * @return boolean
     */
    public boolean isEligableCareGiver() {
        if (getAge() < 18) {
            return false;
        } else if (getAge() - yearsOfExperience < 14) {

            return false;
        } else if (levelOfEducation.equals("None") || levelOfEducation.equals("Elementary")
                || levelOfEducation.equals("Middle School")) {
            return false;
        } else if (yearsOfExperience < 3) {
            return false;
        }
        System.out.println(isFelon + "1");
        return !isFelon;

    }

}
