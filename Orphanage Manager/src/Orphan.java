public class Orphan extends Person implements Skillable{
   
   Skillable orphanSkill;
   private static long orphanID = 400001;
   public Orphan(Orphanage orphanage,String name, int age, String gender, Skillable skill) {
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

   protected long getID(){
      return orphanID;
   }

}
