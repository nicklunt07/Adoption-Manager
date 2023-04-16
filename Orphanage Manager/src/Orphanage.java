import java.util.ArrayList;

public class Orphanage {
    private ArrayList<Employee> employees;
    private ArrayList<Adopter> adopters;
    private ArrayList<Orphan> orphans;

    public Orphanage() {
        employees = new ArrayList<>();
        adopters = new ArrayList<>();
        orphans = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void addAdopter(Adopter adopter) {
        adopters.add(adopter);
    }
    
    public void addOrphan(Orphan orphan) {
        orphans.add(orphan);
    }

    /**
     * remvoing employee wihtout using ID
     * @param employee
     */
    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    /**
     * removing orphans wihtout using ID
     * @param orphan
     */
    public void removeOrphan(Orphan orphan){
        orphans.remove(orphan);
    }

    /**
     * removing adopters without using ID
     * @param adopter
     */
    public void removeAdopter(Adopter adopter){
        adopters.remove(adopter);
    }

    
    /**
     * removing employees using the ID
     * @param id
     */
    public void removeEmployeeById(int id) {
        employees.removeIf(employee -> employee.getID() == id);
    }

    /**
     * removing adopters using the ID
     * @param id
     */
    public void removeAdopterById(int id) {
        adopters.removeIf(adopter -> adopter.getID() == id);
    }

    /**
     * removing orphans using the ID
     * @param id
     */
    public void removeOrphanById(int id) {
        orphans.removeIf(orphan -> orphan.getID() == id);
    }

    /**
     * getting employees
     * @return employees
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * getting adopters
     * @return adopters
     */
    public ArrayList<Adopter> getAdopters() {
        return adopters;
    }

    /**
     * getting orphans
     * @return orphans
     */
    public ArrayList<Orphan> getOrphans() {
        return orphans;
    }
    
    
}

