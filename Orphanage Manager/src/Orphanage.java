import java.util.ArrayList;
import java.util.stream.Collectors;

public class Orphanage {
    private String orphanageName;
    private ArrayList<Person> persons;
    private ArrayList<Employee> employees;
    private ArrayList<Adopter> adopters;
    private ArrayList<Orphan> orphans;

    public Orphanage() {
        persons = new ArrayList<>();
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

    public void addPerson(Person pers){
        persons.add(pers);
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
    public ArrayList<Person> getPersons() {
        return persons;
    }

    public Orphanage getOrphanage(){
        return this;
    }


    /**
     * @param age
     * @return filtered arraylist
     */
    public ArrayList<Orphan> filterByAge(int age){
        ArrayList<Orphan> filteredOrphans = orphans.stream().filter(o -> o.getAge() == age).collect(Collectors.toCollection(ArrayList::new));
        return filteredOrphans;
    }
}