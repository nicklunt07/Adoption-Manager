import java.util.ArrayList;
import java.util.stream.Collectors;

public class Orphanage {
    private String orphanageName;
    private ArrayList<Employee> employees;
    private ArrayList<Adopter> adopters;
    private ArrayList<Orphan> orphans;

    public Orphanage() {
        employees = new ArrayList<>();
        adopters = new ArrayList<>();
        orphans = new ArrayList<>();
        addDefaultEmployees();
        addDefaultOrphans();
    }

    public void addDefaultEmployees(){
        // I am not sure about y'all ages I am just using it test nothing else.
        Employee employee1 = new Employee("Sushanth Ambati", 20, "Male", "Founder", this);
        Employee employee2 = new Employee("Adnan Khaleeli", 20, "Male", "Founder", this);
        Employee employee3 = new Employee("Nick Lunt", 20, "Male", "Founder", this);
        Employee employee4 = new Employee("Zaire Johnson", 20, "Male", "Founder", this);
        //createAndAddEmployee("Sushanth Ambati", 20, "Male", "Founder");
    }

    public void addDefaultOrphans(){
        Orphan orphan1 = new Orphan("Kevin Geiger", 7, "Male", null,this);
        Orphan orphan2 = new Orphan("Mary Cooper", 3, "FeMale", null,this);
        Orphan orphan3 = new Orphan("Howard craig", 7, "Male", null,this);
    }


    // Testing purposes, please dont delete this
    /*public void createAndAddEmployee(String name, int age, String gender, String role) {
        Employee newEmployee = new Employee(name, age, gender, role, this);
        employees.add(newEmployee);
    }*/

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

    /**
     * adds starting 10 orphans (no skills at the moment)
     */


    /**
     * @param age
     * @return filtered arraylist
     */
    public ArrayList<Orphan> filterByAge(int age){
        ArrayList<Orphan> filteredOrphans = orphans.stream().filter(o -> o.getAge() == age).collect(Collectors.toCollection(ArrayList::new));
        return filteredOrphans;
    }
}