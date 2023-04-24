import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javafx.scene.control.Button;

/**
 * This class models an Orphen
 * 
 * @author Adnan Khaleeli and Sushanth Ambati(File IO)
 * @version April 24, 2023
 */
public class Orphan extends Person implements Taskable {

   Skillable orphanSkill;
   private static long orphanID = 400001;
   private long id;
   private List<String> languages;
   private boolean spoken = false;
   public static HashMap<String, Skillable> orphanSkills = new HashMap<>();

   /**
    * Constructor for orphen
    * 
    * @param String       name
    * @param int          age
    * @param String       gender
    * @param Skillable    skill
    * @param Orphanage    orphanage
    * @param List<String> languages
    */
   public Orphan(String name, int age, String gender, Skillable skill, Orphanage orphanage, List<String> languages) {
      super(name, age, gender);
      this.id = orphanID++;
      this.orphanSkill = skill;
      orphanage.addPerson(this);
      this.languages = languages;
   }

   /**
    * Overloaded constructor for orphens who cannot speak
    * 
    * @param String    name
    * @param int       age
    * @param String    gender
    * @param Skillable skill
    * @param Orphanage orphanage
    */
   public Orphan(String name, int age, String gender, Skillable skill, Orphanage orphanage) {
      super(name, age, gender);
      this.id = orphanID++;
      this.orphanSkill = skill;
      orphanage.addPerson(this);

   }

   /**
    * Returns the langauge the orphen can speak
    * 
    * @return List<String>
    */
   public List<String> getLanguages() {
      return languages;
   }

   /**
    * Getter for the Skillable
    @return Skillable orphenSkill
    */
   public Skillable getSkill() {
      return orphanSkill;
   }

   /**
    * Getter for the ID
    * @return long id: 
    */
   protected long getID() {
      return id;
   }

   @Override
   /**
    * ToString of orphen
    * @return String: object details
    */
   public String toString() {
      return super.toString() + "\tID: " + getID();
   }

   /**
    * Assigins a skill
    * @param Skillable skill
    */
   public void assignTask(Skillable skill) {
      this.orphanSkill = skill;
   }

   /**
    * Prints out the Adopter info for the Orphen into a file, for record keeping
    */
   public void AdoptionInfo() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("AdoptionRecord.txt", true))) {
         String adoptionInfo = "Adopted! \nName: " + getName() + "\nAge: " + getAge() + "\nSex: " + getGender()
               + "\n\n";
         bw.write(adoptionInfo);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * Returns true if the orphen speaks the langauge desired
    * @param languagesDesired
    * @return boolean
    */
   public boolean speaksLanguage(ArrayList<String> languagesDesired) {

      if (getAge() < 3) {
         return true;
      }

      spoken = true;
      for (String language : languagesDesired) {
         if (!languages.contains(language)) {
            spoken = false;
            break;
         }
      }

      return spoken;
   }

   /** 
    * Returns the button of the skill the orphen can perform
    */
   public Button performTask() {
      return orphanSkill.skill();
   }

   /**
    * this method adds skills to the OrphanSkillable Hashmap to know which skills orphens can perform
    * @param String key
    * @param Skillable skill
    */
   public static void addOrphanSkillable(String key, Skillable skill) {
      orphanSkills.put(key, skill);
   }

}
