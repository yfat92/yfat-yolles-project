package il.ac.mta.java.exception;

public class StockNotExistException extends Exception{
	
	public StockNotExistException() {
		super("Can’t remove stock, stock's name dosen't exists");
	}
}
