public class Adopter extends Person{
    
    boolean isFelon;
    String criminalRecord;
    boolean isSingle;
    private static long adopterID = 500001;
    public Adopter(Orphanage orphanage, String name, int age, String gender, boolean isFelon, String criminalRecord) {
       super(name, age, gender);
       this.isFelon = isFelon;
       this.criminalRecord = criminalRecord;
       orphanage.addAdopter(this);
    }



    protected long getID(){
        return adopterID;
    }
}
