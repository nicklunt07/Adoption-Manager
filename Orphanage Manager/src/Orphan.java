public class Orphan extends Person implements Skillable{
   
   Skillable orphanSkill;
   private static long orphanID = 400001;
   private long id;
   public Orphan(String name, int age, String gender, Skillable skill, Orphanage orphanage) {
      super(name, age, gender);
      this.id = orphanID++;
      this.orphanSkill = skill;
      orphanage.addOrphan(this);
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
      return id;
   }

   @Override
    public String toString() {
        return super.toString() + "\tID: " + getID();
    }

}
