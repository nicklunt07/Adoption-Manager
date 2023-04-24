/**
 * Employee class
 */
import java.util.HashMap;

import javafx.scene.control.Button;

/**
 * This class models an Employee
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
     * @param String name
     * @param int age
     * @param String gender
     * @param String position
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

    public void setSkill(Skillable skill) {
        this.skill = skill;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static void addEmployeeSkillable(String key, Skillable skill) {
        employeeSkills.put(key, skill);
    }

    public long getId() {
        return this.id;
    }

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

    protected long getID() {

        return id;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getlevelOfEducation() {
        return levelOfEducation;
    }

    public boolean getIsFelon() {
        return isFelon;
    }

    @Override
    public String toString() {
        return super.toString() + "\tID: " + getID() + "\tPosition: " + getPosition();
    }

    public void assignTask(Skillable skill) {
        this.skill = skill;
    }

    public Skillable getSkill() {
        return skill;
    }

    public Button performTask() {
        return skill.skill();
    }

    public String getCriminalRecord() {
        return "This: " + getName() + " is a felon";
    }

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
