import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class Adopter extends Person implements Serializable{
    
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

    /**
     * to Maintain adopter's record
     * @param firstName
     * @param lastName
     * @param mobileNumber
     * @param email
     */
    private void saveAopterInfo(String firstName, String lastName, String mobileNumber, String email) {
        String fileName = "adopter_data.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Mobile Number: " + mobileNumber + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
}
