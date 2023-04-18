import java.util.*;
public class Employee extends Person {
    
    private Skillable skill;
    private String position;
    private static long employeeID = 300001;
    private long id;

    public Employee(String name, int age, String gender, String position,Orphanage orphanage, Skillable skill) {
        super(name,age,gender);
        this.id = employeeID++;
        this.position = position;
        orphanage.addPerson(this);
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

    

    @Override
    public String toString() {
        return super.toString() + "\tID: " + getID() + "\tPosition: " + getPosition();
    }

    public void setSkill(Skillable skill) {
       this.skill = skill;
    }

    public Skillable getSkill() {
        return skill;
    }


   
}
