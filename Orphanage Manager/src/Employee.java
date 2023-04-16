import java.util.*;
public class Employee extends Person implements Skillable {
    
    ArrayList<Skillable> skill;
    private String position;
    private static int employeeID = 300001;

    public Employee(Orphanage orphanage, String name, int age, String gender, String position) {
        super(name,age,gender);
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

    protected int getID(){
        
        return employeeID;
    }

    public void skill() {

    }

   
}
