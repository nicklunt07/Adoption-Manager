import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Orphan extends Person implements Taskable {
   
   Skillable orphanSkill;
   private static long orphanID = 400001;
   private long id;
   public Orphan(String name, int age, String gender, Skillable skill, Orphanage orphanage) {
      super(name, age, gender);
      this.id = orphanID++;
      this.orphanSkill = skill;
      orphanage.addPerson(this);
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

   public void getAdoptionInfo(){
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("AdoptionRecord.txt"))) {
         String adoptionInfo = "Adopted! \nName: " + getName() + "\nAge: " + getAge() + "\nSex: " + getGender();
         bw.write(adoptionInfo);
      } catch (IOException e) {
         e.printStackTrace();
      }
   } 

}
