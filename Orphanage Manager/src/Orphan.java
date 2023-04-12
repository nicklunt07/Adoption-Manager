public class Orphan extends Person implements Skillable{
   
    Skillable orphanSkill;
     public Orphan(String name, int age, String gender, Skillable skill) {
        super(name, age, gender);
        this.orphanSkill = skill;
     }

     public void skill() {
        orphanSkill.skill();
     }

     public Skillable getSkill() {
        return orphanSkill;
     }
     
     public void makeVideoSkill() {

     }

    
}
