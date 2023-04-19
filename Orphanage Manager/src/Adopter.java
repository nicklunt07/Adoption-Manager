public class Adopter extends Person{
    
    boolean isFelon;
    boolean criminalRecord;
    boolean isSingle;
    private static long adopterID = 500001;
    public Adopter(Orphanage orphanage, String name, int age, String gender, boolean isFelon, boolean criminalRecord) {
       super(name, age, gender);
       this.isFelon = isFelon;
       this.criminalRecord = criminalRecord;
       //addPerson(this);
       orphanage.addPerson(this);
    }



    protected long getID(){
        return adopterID;
    }
}
