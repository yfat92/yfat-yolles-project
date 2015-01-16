package il.ac.mta.java.exception;
/**
 * Constructs a new exception with the specified detail message (quantity) 
 * @author yfat yolles
 * @since 15/01/15
 */
public class QuantityInvalidException extends Exception{
	public QuantityInvalidException(int quantity) {
		
		super("the quantity you is invalid, you ask to sell "+quantity );
	}
}
