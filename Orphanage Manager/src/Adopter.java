import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Models an adopter object
 * 
 * @author Nick Lunt
 * @version April 24, 2023
 */
public class Adopter extends Person implements Responsible {

    private boolean isFelon;
    private double income;
    private static long adopterID = 500001;
    private int dependencies;

    /**
     * Constructor for Adopter
     * 
     * @param Orphanage orphanage
     * @param String    name
     * @param int       age
     * @param String    gender
     * @param boolean   isFelon
     * @param double    income
     * @param int       dependencies
     */
    public Adopter(Orphanage orphanage, String name, int age, String gender, boolean isFelon, double income,
            int dependencies) {
        super(name, age, gender);
        this.isFelon = isFelon;
        this.income = income;
        this.dependencies = dependencies;
        // addPerson(this);
        orphanage.addPerson(this);
    }

    /**
     * returns the income
     * 
     * @return double income
     */
    public double getIncome() {
        return this.income;
    }

    /**
     * Retruns double income
     * 
     * @param double income
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Returns dependencies
     * 
     * @return int dependenices
     */
    public int getDependencies() {
        return this.dependencies;
    }

    /**
     * Set dependencies
     * 
     * @param dependencies
     */
    public void setDependencies(int dependencies) {
        this.dependencies = dependencies;
    }

    /**
     * Return boolean if adopter is eligable
     * 
     * @return boolean
     */
    public boolean isEligableCareGiver() {
        if ((income / dependencies) <= 25000) {
            return false;
        } else if (getAge() < 18) {
            return false;
        }

        return (!isFelon);
    }

    /**
     * Gets criminal record of adopter
     */
    public String getCriminalRecord() {
        if (isFelon) {
            return "Felon";
        }

        return "Not a Felon";
    }

    /**
     * Gets the ID of the adopter
     * 
     * @return long id
     */
    protected long getID() {
        return adopterID;
    }

    /**
     * to Maintain adopter's record
     * @author Sushanth
     * @param String firstName
     * @param String lastName
     * @param String mobileNumber
     * @param String email
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
