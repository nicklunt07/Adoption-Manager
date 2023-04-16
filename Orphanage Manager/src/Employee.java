import java.util.*;
public class Employee extends Person implements Skillable {
    
    ArrayList<Skillable> skill;
    private String position;

    public Employee(Orphanage orphanage, String name, int age, String gender, String position) {
        super(name,age,gender);
        //addPerson(this);
        this.position = position;
        orphanage.addEmployee(this);
    }

    /**
     * @author Sushanth Ambati
     * @return position
     */
    protected String getPosition(){
        return this.position;
    }

    public void skill() {

    }

   
}
