import java.util.*;
public class Employee extends Person implements Skillable {
    ArrayList<Skillable> skill;
    public Employee(String name, int age, String gender) {
        super(name,age,gender);
        addPerson(this);
    }

   public void skill() {

   }

   
}
