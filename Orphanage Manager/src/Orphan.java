public class Orphan extends Person implements Skillable{
   
   Skillable orphanSkill;
   private static int orphanID = 400001;
   public Orphan(String name, int age, String gender, Skillable skill) {
      super(name, age, gender);
      //orphanage.addOrphan(this);
      this.orphanSkill = skill;
   }

   public void skill() {
      orphanSkill.skill();
   }

   public Skillable getSkill() {
      return orphanSkill;
   }

   public void makeVideoSkill(String filename) {

   }

   protected int getID(){
      return orphanID;
   }

}
