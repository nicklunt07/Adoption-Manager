public class Orphan extends Person implements Skilable{
   
    public Orphan(String name, int age, String gender) {
        super(name, age, gender);
     }

     public Orphan(String name, int age, String gender, Skilable skill) {
        super(name, age, gender);
     }

     public void skill() {

     }
}
