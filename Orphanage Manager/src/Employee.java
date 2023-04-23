

import javafx.scene.control.Button;
public class Employee extends Person implements Taskable {
    
    private Skillable skill;
    private String position;
    private static long employeeID = 300001;
    private long id;
    private int yearsOfExperience; 
    private String levelOfEducation; 
    private boolean isFelon;

    public Employee(String name, int age, String gender, String position,Orphanage orphanage, Skillable skill, int yearOfExprience, String levelOfEducation, boolean isFelon) {
        super(name,age,gender);
        this.id = employeeID++;
        this.position = position;
        this.skill = skill;
        orphanage.addPerson(this);
    }
    public void setSkill(Skillable skill) {
        this.skill = skill;
    }
    public void setPosition(String position) {
        this.position = position;
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
    protected String getPosition(){
        return this.position;
    }

    protected long getID(){
        
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


   
}
