/**
 * @author Sushanth Ambati
 * Customized handling incase if the adopter is not eligible for adopting
 */
public class InvalidAdoptionException extends Exception {
    public InvalidAdoptionException(String message) {
        super(message);
    }
}

