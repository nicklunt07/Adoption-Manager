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
    
}

