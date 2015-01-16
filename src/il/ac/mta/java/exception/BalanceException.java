package il.ac.mta.java.exception;
/**
 * Constructs a new exception with the specified detail message (balance) 
 * @author yfat yolles
 * @since 15/01/15
 */
public class BalanceException extends Exception {
	
	public BalanceException(float balance) {
		super("Can’t buy stock, your balance is: " +balance );
	}
}
