import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Adopter extends Person implements Responsible{
    
    private boolean isFelon;
    private boolean isSingle;
    private double income;
    private static long adopterID = 500001;
    private int dependencies;
    public Adopter(Orphanage orphanage, String name, int age, String gender, boolean isFelon, double income, int dependencies) {
       super(name, age, gender);
       this.isFelon = isFelon;
       this.income = income;
       this.dependencies = dependencies;
       //addPerson(this);
       orphanage.addPerson(this);
    }


   

    public boolean isIsSingle() {
        return this.isSingle;
    }


    public double getIncome() {
        return this.income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(int dependencies) {
        this.dependencies = dependencies;
    }

    // to be implemented later
    public boolean isEligableCareGiver(){
        if((income/dependencies) <= 25000) {
           return false;
        }

        return (!isFelon);
    }

    public String getCriminalRecord() {
        if(isFelon) {
            return "Felon";
        }

        return "Not a Felon";
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
