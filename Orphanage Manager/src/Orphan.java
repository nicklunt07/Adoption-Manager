public class Orphan extends Person implements Skillable{
   
   Skillable orphanSkill;
     public Orphan(String name, int age, String gender, Skillable skill) {
        super(name, age, gender);
        //addPerson(this);
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

}
