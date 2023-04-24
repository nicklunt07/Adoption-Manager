import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/** 
 * This class models an Orphange and various attributes and operations
 * @author Adnan Khaleeli, Nick Lunt, Sushanth Ambati
 * @version April 23, 2023
 */
public class Orphanage implements Serializable {
    // private String orphanageName;
    private ArrayList<Person> persons;

    List<Orphan> possibleOrphans;
    public static HashMap<String, Skillable> skills = new HashMap<String, Skillable>();
    private static final Logger LOGGER = Logger.getLogger(Orphanage.class.getName());

    /**
     * Constructor for Orphanage 
     */
    public Orphanage() {
        persons = new ArrayList<>();
        addDefaultEmployees();
        addDefaultOrphans();
    }

    /**
     * Adds default Employees
     * @author Sushanth Ambati
     */
    private void addDefaultEmployees() {
        Employee employee1 = new Employee("Sushanth Ambati", 20, "Male", "Founder", this, skills.get("Cooking"), 4, "High School", false);
        Employee employee2 = new Employee("Adnan Khaleeli", 23, "Male", "Founder", this, skills.get("DishWashing"), 3, "College", false );
        Employee employee3 = new Employee("Nick Lunt", 25, "Male", "Founder", this, skills.get("Manager"),5,"Masters", false);
        Employee employee4 = new Employee("Zaire Johnson", 30, "Male", "Founder", this, skills.get("Ironing"), 8, "High School", false);

    }

    /**
     * Adding default orphans 
     * @author Sushanth Ambati and Adnan Khaleeli
     */
    private void addDefaultOrphans() {
        Orphan orphan1 = new Orphan("Kevin Geiger", 7, "Male", skills.get("Math"), this,
                Arrays.asList("Spanish", "English"));
        Orphan orphan2 = new Orphan("Mary Cooper", 3, "Female", skills.get("Painting"), this, Arrays.asList("English"));
        Orphan orphan3 = new Orphan("Howard Craig", 7, "Male", skills.get("Circle"), this,
                Arrays.asList("Spanish", "English"));
        Orphan orphan4 = new Orphan("Keanu Hardin", 5, "Male", skills.get("Running"), this,
                Arrays.asList("Spanish", "English"));
        Orphan orphan5 = new Orphan("Barnaby Kane", 1, "Male", skills.get("Skateboarding"), this);
        Orphan orphan6 = new Orphan("Safia Palmer", 10, "Female", skills.get("Biking"), this, Arrays.asList("English"));
        Orphan orphan7 = new Orphan("Mattie Odom", 11, "Female", skills.get("JumpRoping"), this,
                Arrays.asList("Spanish", "English"));
        Orphan orphan8 = new Orphan("Dale Byrne", 16, "Male", skills.get("BottleFlip"), this, Arrays.asList("English"));
        Orphan orphan9 = new Orphan("Andrea Elizabeth", 0, "Female", skills.get("Gardening"), this);
        Orphan orphan10 = new Orphan("Kenneth Holman", 4, "Male", skills.get("BasketBall"), this,
                Arrays.asList("English", "Spanish"));
        Orphan orphan11 = new Orphan("Sidney Gaines", 16, "Female", skills.get("Soccer"), this,
                Arrays.asList("English", "Spanish"));
        // Orphan orphan12 = new Orphan("Nettie Nash", 17, "Female", null,this);
        // Orphan orphan13 = new Orphan("Rajan O'Quinn", 8, "Male", null,this);
    }

  
    
    /**
     * Adding any person to the polymorphic collection
     * @param Person pers: The Person
     */
    public void addPerson(Person pers) {
        persons.add(pers);
    }

    /**
     * Removes adpted Child from the polymorphic collection
     * @param child
     */
    public void removeAdoptedChild(Orphan child) {
        // Remove child from the persons list
        persons.remove(child);
    }

    /**
     * Remvoing employee wihtout using ID
     * @param Employee employee
     */
    public void removeEmployee(Employee employee) {
        persons.remove(employee);
    }

    /**
     * Removing orphans wihtout using ID
     * @param Orphan orphan
     */
    public void removeOrphan(Orphan orphan) {
        persons.remove(orphan);
    }

    /**
     * removing adopters without using ID
     * @param Adopter adopter
     */
    public void removeAdopter(Adopter adopter) {
        persons.remove(adopter);
    }

    /**
     * removing employees using the ID
     * @param int id
     */
    public void removeEmployeeById(int id) {

        persons.removeIf(employee -> employee.getID() == id);
    }

    /**
     * removing adopters using the ID
     * @param int id
     */
    public void removeAdopterById(int id) {
        persons.removeIf(adopter -> adopter.getID() == id);
    }

    /**
     * removing orphans using the ID
     * @param int id
     */
    public void removeOrphanById(int id) {
        persons.removeIf(orphan -> orphan.getID() == id);
    }

    /**
     * Getting employees
     * @return employees
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Returning the orphanage
     * @return this
     */
    public Orphanage getOrphanage() {
        return this;
    }

    /**
     * Returning the HashMap of possible skills
     * @return HashMap<String, Skillable> 
     */
    public HashMap<String, Skillable> getSkills() {
        return skills;
    }

    /**
     * Adding skills.
     * @author Adnan Khaleeli
     */
    static {

        // Some Employee Skills
        skills.put("DishWashing", () -> createButton("Orphanage Manager/Assets/DishWashing.mp4", "employee"));
        skills.put("Cooking", () -> createButton("Orphanage Manager/Assets/Cooking.mp4","employee"));
        skills.put("Ironing", () -> createButton("Orphanage Manager/Assets/Ironing.mp4", "employee"));
        skills.put("Manager", () -> createButton("Orphanage Manager/Assets/Manager.mp4", "employee"));
        // End of the employee skills

        // Some orphan skills
        skills.put("Math", () -> createButton("Orphanage Manager/Assets/Math.mp4", "orphan"));
        skills.put("Skateboarding", () -> createButton("Orphanage Manager/Assets/Skateboarding.mp4" , "orphan"));
        skills.put("Biking", () -> createButton("Orphanage Manager/Assets/Biking.mp4", "orphan"));
        skills.put("Gardening", () -> createButton("Orphanage Manager/Assets/Gardening.mp4", "orphan"));
        skills.put("JumpRoping", () -> createButton("Orphanage Manager/Assets/JumpRoping.mp4", "orphan"));
        skills.put("Painting", () -> createButton("Orphanage Manager/Assets/Painting.mp4", "orphan"));
        skills.put("Running", () -> createButton("Orphanage Manager/Assets/Running.mp4", "orphan"));
        skills.put("Circle", () -> createButton("Orphanage Manager/Assets/Circle.mp4", "orphan"));
        skills.put("BottleFlip", () -> createButton("Orphanage Manager/Assets/BottleFlip.mp4", "orphan"));
        skills.put("BasketBall", () -> createButton("Orphanage Manager/Assets/Basketball.mp4", "orphan"));
        skills.put("Soccer", () -> createButton("Orphanage Manager/Assets/Soccer.mp4", "orphan"));
        

    }

    /**
     * Creating the skillables
     * @author Adnan Khaleeli
     * @param String filename
     * @return Button
     */
    public static Button createButton(String filename, String type) {

        Button button = new Button("Skill");

        button.setOnAction(event -> {
            WebView postFeed = new WebView();
            WebEngine postEngine = postFeed.getEngine();
            postFeed.setPrefHeight(200);
            postFeed.setPrefWidth(320);
            File file = new File(filename);
            String html = " <video width='300' autoplay>  <source src='" + file.toURI()
                    + "' type='video/mp4' />" + "Your browser does not support the video element.</video> ";
            postEngine.loadContent(html);

            Stage stage = new Stage();
            String[] parts = filename.split("/");
            String fileNameWithExtension = parts[parts.length - 1];
            String fileNameWithoutExtension = fileNameWithExtension.split("\\.")[0];
            stage.setTitle(fileNameWithoutExtension);

            // Set the size of the MediaView to match the size of the video

            VBox box = new VBox();
            box.getChildren().add(postFeed);
            Scene scene = new Scene(box);

            stage.setScene(scene);
            stage.show();

        });
        String[] parts = filename.split("/");
            String fileNameWithExtension = parts[parts.length - 1];
            String fileNameWithoutExtension = fileNameWithExtension.split("\\.")[0];
            Skillable skill = ()-> button; 
        if(type.equals("employee")) {
          
          Employee.addEmployeeSkillable(fileNameWithoutExtension, skill);
        } else {
            Orphan.addOrphanSkillable(fileNameWithoutExtension, skill);
        }
        return button;
    }

    /**
     * Serializing the orphanage
     * @author Sushanth Ambati
     */
    public void serializeOrphanage() {
        try {
            FileOutputStream fileOut = new FileOutputStream("orph.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            LOGGER.log(Level.INFO, "Orphanage serialized successfully.\n");
        } catch (IOException i) {
            LOGGER.log(Level.SEVERE, "Error during orphanage serialization.\n", i);
        }
    }

    /**
     * Deseralizing the orphanage
     * @author Sushanth Ambati 
     * @return Orphanage 
     */
    public static Orphanage deserializeOrphanage() {
        Orphanage deserializedOrphanage = null;
        try {
            FileInputStream fileIn = new FileInputStream("orph.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedOrphanage = (Orphanage) in.readObject();
            in.close();
            fileIn.close();
            LOGGER.log(Level.INFO, "Orphanage deserialized successfully.\n");
        } catch (IOException i) {
            LOGGER.log(Level.WARNING, "No Serialized file found during orphanage deserialization yet.\n");
        } catch (ClassNotFoundException c) {
            LOGGER.log(Level.SEVERE, "Class not found during orphanage deserialization.\n", c);
        }
        return deserializedOrphanage;
    }

    /**
     * Creating new orphan
     * @author Nick Lunt
     * @param String name
     * @param String age
     * @param String gender
     * @param String skill
     * @param String languages
     */
    public void createNewOrphan(String name, String age, String gender, String skill, String languages) {
        try{
        Orphan newOrphan = new Orphan(name, Integer.parseInt(age), gender, Orphanage.skills.get(skill), this,
                Arrays.asList(languages));

        }catch(Exception e) {
            Stage warningStage = new Stage();
            Label warningLabel = new Label("One or more field is invalid, try again!");
            VBox warningBox = new VBox(10, warningLabel);
            warningBox.setPadding(new Insets(10));
            warningBox.setAlignment(Pos.CENTER);
            Scene warningScene = new Scene(warningBox, 200, 200);
            warningStage.setScene(warningScene);
            warningStage.setTitle("Invalid Information");
            warningStage.show();
        }
    }

}