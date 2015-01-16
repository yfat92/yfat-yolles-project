package il.ac.mta.java.exception;
/**
 * Constructs a new exception with the specified detail message (symbol) 
 * @author yfat yolles
 * @since 15/01/15
 */
public class StockAlreadyExistsException extends Exception{
	public StockAlreadyExistsException(String symbol) {
		
		super("Stock " + symbol + " already exists!");
	}
}