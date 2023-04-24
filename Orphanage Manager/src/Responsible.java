import java.io.Serializable;

/**
 * Interfaces called Responsible that models the bahvior or someone responsible
 * @author Adnan Khaleeli
 * @version April 23, 2023
 */
public interface Responsible extends Serializable{

    /**
     * Retrives wheather this person is fit to be a careGiver
     * @return boolean
     */
    public boolean isEligableCareGiver();

    /**
     * Returns the criminal Record on the person in question
     * @return String criminalRecord
     */
    public String getCriminalRecord();

     
}
