package il.ac.mta.java.exception;

public class BalanceException extends Exception {
	
	public BalanceException(float balance) {
		super("Can’t buy stock, your balance is: " +balance );
	}
}
