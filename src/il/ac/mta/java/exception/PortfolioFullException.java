package il.ac.mta.java.exception;
/**
 * Constructs a new exception with null as its detail message.
 * The cause is not initialized, and be initialized by a call to super
 * @author yfat yolles
 * @since 15/01/15
 */
public class PortfolioFullException extends Exception {
	public PortfolioFullException() {
		super("You had reached maximum portfolio size!");
	}
}
