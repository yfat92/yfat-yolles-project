package il.ac.mta.java.exception;
/**
 * Constructs a new exception with null as its detail message.
 * The cause is not initialized, and be initialized by a call to super
 * @author yfat yolles
 * @since 15/01/15
 */
public class StockNotExistException extends Exception{
	
	public StockNotExistException() {
		super("Can’t remove stock, stock's name dosen't exists");
	}
}
