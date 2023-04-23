import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Orphanage implements Serializable{
    //private String orphanageName;
    private ArrayList<Person> persons;
    private ArrayList<Employee> employees;
    private ArrayList<Adopter> adopters;
    private ArrayList<Orphan> orphans;
    List<Orphan> possibleOrphans;
    public static HashMap<String, Skillable> skills = new HashMap<String, Skillable>();

    public Orphanage() {
        persons = new ArrayList<>();
        employees = new ArrayList<>();
        adopters = new ArrayList<>();
        orphans = new ArrayList<>();
        addDefaultEmployees();
        addDefaultOrphans();
        addDefaultAdopters();
    }

    public void addDefaultEmployees(){
        // I am not sure about y'all ages I am just using it test nothing else.
        Employee employee1 = new Employee("Sushanth Ambati", 20, "Male", "Founder", this, skills.get("Cooking"));
        Employee employee2 = new Employee("Adnan Khaleeli", 20, "Male", "Founder",this, skills.get("DishWashing"));
        Employee employee3 = new Employee("Nick Lunt", 20, "Male", "Founder", this, skills.get("Manager"));
        Employee employee4 = new Employee("Zaire Johnson", 20, "Male", "Founder", this, skills.get("Ironing"));

    }

    public void addDefaultOrphans(){
        Orphan orphan1 = new Orphan("Kevin Geiger", 7, "Male", skills.get("Math"),this, Arrays.asList("Spanish", "English"));
        //Orphan orphan2 = new Orphan("Mary Cooper", 3, "Female", skills.get("Painting"),this,Arrays.asList("English"));
        Orphan orphan3 = new Orphan("Howard Craig", 7, "Male", skills.get("Circle"),this,Arrays.asList("Spanish", "English"));
        Orphan orphan4 = new Orphan("Keanu Hardin", 5, "Male", skills.get("Running"),this,Arrays.asList("Spanish", "English"));
        Orphan orphan5 = new Orphan("Barnaby Kane", 1, "Male", skills.get("Skateboarding"),this);
        //Orphan orphan6 = new Orphan("Safia Palmer", 10, "Female", skills.get("Biking"),this,Arrays.asList("English"));
        Orphan orphan7 = new Orphan("Mattie Odom", 11, "Female", skills.get("JumpRoping"),this,Arrays.asList("Spanish", "English"));
        Orphan orphan8 = new Orphan("Dale Byrne", 16, "Male", skills.get("BottleFlip"),this, Arrays.asList("English"));
        Orphan orphan9 = new Orphan("Andrea Elizabeth", 0, "Female", skills.get("Gardening"),this);
        Orphan orphan10 = new Orphan("Kenneth Holman", 4, "Male", skills.get("BasketBall"),this, Arrays.asList("English", "Spanish"));
        Orphan orphan11 = new Orphan("Sidney Gaines", 16, "Female", skills.get("Soccer"),this, Arrays.asList("English", "Spanish"));
        // Orphan orphan12 = new Orphan("Nettie Nash", 17, "Female", null,this);
        // Orphan orphan13 = new Orphan("Rajan O'Quinn", 8, "Male", null,this);
    }

    public void addDefaultAdopters(){
        Adopter adopter1 = new Adopter(this, "Kevin Koett", 27, "Male", false, 100000, 3);
        Adopter adopter2 = new Adopter(this, "Kevin Carr", 37, "Male", true, 60000, 2);
        Adopter adopter3 = new Adopter(this, "Kevin Hof", 30, "Male", false, 100000, 5);
    }


    // Testing purposes, please dont delete this
    /*public void createAndAddEmployee(String name, int age, String gender, String role) {
        Employee newEmployee = new Employee(name, age, gender, role, this);
        employees.add(newEmployee);
    }*/

    public void addPerson(Person pers){
        persons.add(pers);
    }

    public void removeAdoptedChild(Orphan child) {
        // Remove child from the persons list
        persons.remove(child);
        // Remove child from the possibleOrphans list
        //possibleOrphans.remove(child);
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


    public HashMap<String, Skillable> getSkills() {
        return skills;
    }

   
     static {

        // Some Employee Skills
        skills.put("DishWashing", () -> createButton("Orphanage Manager/Assets/DishWashing.mp4"));
        skills.put("Cooking", () -> createButton("Orphanage Manager/Assets/Cooking.mp4"));
        skills.put("Ironing", () -> createButton("Orphanage Manager/Assets/Ironing.mp4"));
        skills.put("Manager", () -> createButton("Orphanage Manager/Assets/Manager.mp4"));
        // End of the employee skills

        //Some orphan skills
        skills.put("Math", () -> createButton("Orphanage Manager/Assets/Math.mp4"));
        skills.put("Skateboarding", () -> createButton("Orphanage Manager/Assets/Skateboarding.mp4"));
        skills.put("Biking", () -> createButton("Orphanage Manager/Assets/Biking.mp4"));
        skills.put("Gardening", () -> createButton("Orphanage Manager/Assets/Gardening.mp4"));
        skills.put("JumpRoping", () -> createButton("Orphanage Manager/Assets/JumpRoping.mp4"));
        skills.put("Painting", () -> createButton("Orphanage Manager/Assets/Painting.mp4"));
        skills.put("Running", () -> createButton("Orphanage Manager/Assets/Running.mp4"));
        skills.put("Circle", () -> createButton("Orphanage Manager/Assets/Circle.mp4"));
        skills.put("BottleFlip" ,() -> createButton("Orphanage Manager/Assets/BottleFlip.mp4"));
        skills.put("BasketBall" ,() -> createButton("Orphanage Manager/Assets/Basketball.mp4"));
        skills.put("Soccer" ,() -> createButton("Orphanage Manager/Assets/Soccer.mp4"));

           
        
     }

    public static Button createButton(String filename) {
       
        Button button = new Button("Skill");
        
        button.setOnAction(event -> { 
            WebView postFeed = new WebView();
            WebEngine postEngine = postFeed.getEngine();
            postFeed.setPrefHeight(200);
		    postFeed.setPrefWidth(320);
            File file = new File(filename);
            String html =  " <video width='300' autoplay>  <source src='" + file.toURI() 
            +   "' type='video/mp4' />" + "Your browser does not support the video element.</video> ";
            postEngine.loadContent(html);


            Stage stage = new Stage();
            String[] parts = filename.split("/");
            String fileNameWithExtension = parts[parts.length-1];
            String fileNameWithoutExtension = fileNameWithExtension.split("\\.")[0];
            stage.setTitle(fileNameWithoutExtension);
            
            // Set the size of the MediaView to match the size of the video
            
            
            VBox box = new VBox();
            box.getChildren().add(postFeed);
            Scene scene = new Scene(box);
            
            stage.setScene(scene);
            stage.show();
        
        });

        return button;
    }
    
    public void serializeOrphanage() {
        try {
            FileOutputStream fileOut = new FileOutputStream("orph.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Orphanage deserializeOrphanage() throws FileNotFoundException {
        Orphanage deserializedOrphanage = null;
        try {
            FileInputStream fileIn = new FileInputStream("orph.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedOrphanage = (Orphanage) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return deserializedOrphanage;
    }

    public void copyFrom(Orphanage other) {
        this.persons = other.persons;
    }
    
    
    

}