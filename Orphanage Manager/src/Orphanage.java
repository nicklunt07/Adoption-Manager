import java.util.ArrayList;

public class Orphanage {
    String orphanageName;
    ArrayList<Orphan> orphans = new ArrayList<>();
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
    
    public Orphanage(String orphanageName){
        this.orphanageName = orphanageName;

    }

    public void addOrphans(){
        orphans.add(new Orphan("Keanu Hardin", 5, "Male", null));
        orphans.add(new Orphan("Barnaby Kane", 1, "Male", null));
        orphans.add(new Orphan("Safia Palmer", 3, "Female", null));
        orphans.add(new Orphan("Mattie Odom", 11, "Male", null));
        orphans.add(new Orphan("Dale Byrne", 16, "Male", null));
        orphans.add(new Orphan("Kaider Blackburn", 0, "Male", null));
        orphans.add(new Orphan("Kenneth Holman", 2, "Male", null));
        orphans.add(new Orphan("Sidney Gaines", 7, "Female", null));
        orphans.add(new Orphan("Nettie Nash", 5, "Female", null));
        orphans.add(new Orphan("Rajan O'Quinn", 8, "Male", null));
    }

    public ArrayList<Orphan> getOrphans(){
        return orphans;
    }
}


}