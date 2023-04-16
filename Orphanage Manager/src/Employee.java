import java.util.*;
public class Employee extends Person implements Skillable {
    
    ArrayList<Skillable> skill;
    private String position;

    public Employee(String name, int age, String gender, String position) {
        super(name,age,gender);
        this.position = position;
        addPerson(this);
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
