import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javafx.scene.control.Button;

public class Orphan extends Person implements Taskable{
   
   Skillable orphanSkill;
   private static long orphanID = 400001;
   private long id;
   private List<String> languages;
   private boolean spoken = false;
   public static HashMap<String, Skillable> orphanSkills = new HashMap<>();
   public Orphan(String name, int age, String gender, Skillable skill, Orphanage orphanage, List<String> languages) {
      super(name, age, gender);
      this.id = orphanID++;
      this.orphanSkill = skill;
      orphanage.addPerson(this);
      this.languages = languages; 
   }

   public Orphan(String name, int age, String gender, Skillable skill, Orphanage orphanage) {
      super(name, age, gender);
      this.id = orphanID++;
      this.orphanSkill = skill;
      orphanage.addPerson(this);
      
   }
   public List<String> getLanguages() {
      return languages;
   }

   

   public Skillable getSkill() {
      return orphanSkill;
   }

   public void makeVideoSkill(String filename) {

   }

   protected long getID(){
      return id;
   }

   @Override
    public String toString() {
        return super.toString() + "\tID: " + getID();
    }

    public void assignTask(Skillable skill) {
      this.orphanSkill = skill;
    }

   public void AdoptionInfo(){
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("AdoptionRecord.txt",true))) {
         String adoptionInfo = "Adopted! \nName: " + getName() + "\nAge: " + getAge() + "\nSex: " + getGender()+ "\n\n";
         bw.write(adoptionInfo);
      } catch (IOException e) {
         e.printStackTrace();
      }
   } 

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

   public Button performTask() {
      return orphanSkill.skill();
   }

   public static void addOrphanSkillable(String key, Skillable skill) {
      Orphanage.skills.put(key, skill);
      orphanSkills.put(key, skill);
  }


}
