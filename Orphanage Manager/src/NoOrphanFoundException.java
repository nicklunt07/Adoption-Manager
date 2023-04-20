/**
 * @author Sushanth Ambati
 * Customized handling incase if the adopter is not eligible for adopting
 */
public class NoOrphanFoundException extends RuntimeException {
    public NoOrphanFoundException(String message) {
        super(message);
    }
}

