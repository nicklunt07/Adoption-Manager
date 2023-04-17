import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Orphanage {
    private String orphanageName;
    private ArrayList<Person> persons;
    private ArrayList<Employee> employees;
    private ArrayList<Adopter> adopters;
    private ArrayList<Orphan> orphans;
    private static HashMap<String, Skillable> skills;

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
        Orphan orphan2 = new Orphan("Mary Cooper", 3, "Female", null,this);
        Orphan orphan3 = new Orphan("Howard Craig", 7, "Male", null,this);
        Orphan orphan4 = new Orphan("Keanu Hardin", 5, "Male", null,this);
        Orphan orphan5 = new Orphan("Barnaby Kane", 1, "Male", null,this);
        Orphan orphan6 = new Orphan("Safia Palmer", 3, "Female", null,this);
        Orphan orphan7 = new Orphan("Mattie Odom", 11, "Male", null,this);
        Orphan orphan8 = new Orphan("Dale Byrne", 16, "Male", null,this);
        Orphan orphan9 = new Orphan("Kaider Blackburn", 0, "Male", null,this);
        Orphan orphan10 = new Orphan("Kenneth Holman", 2, "Male", null,this);
        Orphan orphan11 = new Orphan("Sidney Gaines", 7, "Female", null,this);
        Orphan orphan12 = new Orphan("Nettie Nash", 5, "Female", null,this);
        Orphan orphan13 = new Orphan("Rajan O'Quinn", 8, "Male", null,this);
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

    public HashMap<String, Skillable> getSkills() {
        return skills;
    }

    static {

        skills.put("Cleaning", () -> createButton("DishWashing.mp4"));
            
      

       

           
            
        
    }

    public static Button createButton(String filename) {
        String [] list = filename.split(".");
        Button button = new Button(list[0]);
        button.setOnAction(event -> { 
            Stage stage = new Stage();
            File file = new File(filename);
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            
            // Set the size of the MediaView to match the size of the video
            mediaView.setFitWidth(media.getWidth());
            mediaView.setFitHeight(media.getHeight());
            
            VBox box = new VBox();
            box.getChildren().add(mediaView);
            Scene scene = new Scene(box);
            stage.setScene(scene);
            stage.show();
        });

        return button;
    }
}