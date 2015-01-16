package il.ac.mta.java.model;

import java.util.Date;

import il.ac.mta.java.exception.BalanceException;
import il.ac.mta.java.exception.PortfolioFullException;
import il.ac.mta.java.exception.QuantityInvalidException;
import il.ac.mta.java.exception.StockAlreadyExistsException;
import il.ac.mta.java.exception.StockNotExistException;
import il.ac.mta.java.model.StockStatus;
import il.ac.mta.java.model.Stock;

/**
 * the class save portfolio data 
 *  and also create a copy of Portfolio 
 * @author Yfat Yolles
 * @since 3/12/2014
 * date 13/12/2014
 */
public class Portfolio {
	//members
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private int portfolioSize = 0;
	private float balance ;
	int i =0;

	//array

	StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.balance = portfolio.getBalance();
		for (int i = 0; i < portfolioSize; i++)
		{
			this.stocksStatus[i] = new StockStatus(getStocksStatus()[i]);
		}
		
	}

	//constructor
	public Portfolio(String title, int portfolioSize, Stock [] stocks, StockStatus [] stockStatus
			,float balance){
		this.title = title;
		this.portfolioSize = portfolioSize;
		this.balance = balance;
		for (int i = 0; i < portfolioSize; i++)
		{
			this.stocksStatus[i] = stockStatus[i];
		}
	}

	//methods
	/**
	*This method add stock to the array.
	*it's also do validation, if the client  
	*ask to add stock with a symbol that already exists
	*/
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {
		int countStockSymbol =0;

		// find the symbol's index and count 
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocksStatus[i].getSymbol() ==  stock.getSymbol()){
				countStockSymbol++;
			}
		}
		if(countStockSymbol == 1) {
			throw new StockAlreadyExistsException(stock.getSymbol());
		}
		if(portfolioSize >= MAX_PORTFOLIO_SIZE) {
			throw new PortfolioFullException();
		}

		
		stocksStatus[portfolioSize] = new StockStatus(stock.getSymbol()
				, stock.getBid(), stock.getAsk(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		portfolioSize++;
	}
	/**
	*This method remove stock from stock's array and sell 
	* all the stock's quantity
	* @return boolean if the remove succeed
	*/
	public void removeStock(String symbol ) throws StockNotExistException, QuantityInvalidException{		
		boolean stockIsExisist = false;		
		int StockSymbolIndex = 0;

		// find the index of symbol
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocksStatus[i].getSymbol() ==  symbol){
				stockIsExisist = true;
				StockSymbolIndex = i;
			}
		}

		// if stock symbol dosen't exists
		if(stockIsExisist == false){
			throw new StockNotExistException();	
		}
		// if stock index is the last
		if (StockSymbolIndex == MAX_PORTFOLIO_SIZE - 1){
			sellStock(symbol, stocksStatus[StockSymbolIndex].getStockQuantity());
			portfolioSize--;
		}
		
		if (stockIsExisist == true){
			sellStock(symbol, stocksStatus[StockSymbolIndex].getStockQuantity());
			stocksStatus[StockSymbolIndex] = stocksStatus[portfolioSize-1];
			portfolioSize--;
		}
	}
	/**
	*This method buy stocks according to the quantity    
	*  
	* @return boolean if the buy succeed
	*/
	public void buyStock(String symbol, int quantity )throws BalanceException ,StockNotExistException{
		boolean buyStockSucsses = false;
		int stockSymbolIndex = 0;

		if (balance <=0){
			throw new BalanceException(balance);
		}
		// find the index of symbol
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocksStatus[i].getSymbol() ==  symbol){
				buyStockSucsses = true;
				stockSymbolIndex = i;
			}
		}
		// if the stock's name dosen't exists
		if(buyStockSucsses == false){
			throw new StockNotExistException();
		}
		if (quantity == -1 ){
			int numOfStocks = (int)(Math.floor((double)(balance/stocksStatus[stockSymbolIndex].ask)));
			this.stocksStatus[stockSymbolIndex].stockQuantity += numOfStocks;
			updateBalance(-numOfStocks * stocksStatus[stockSymbolIndex].ask);
		}

		updateBalance(-quantity * this.stocksStatus[stockSymbolIndex].ask);
		this.stocksStatus[stockSymbolIndex].stockQuantity += quantity;

	}
	/**
	*This method sell stock and remove it from the array.
	*If the quantity is equal or bigger then stock's quantity.
	*it's also do validation, if the client add invalid quantity.
	*The method update costumer's balance and number of stocks that the customer-owned
	*@return whether the sale succeeded
	*/
	public void sellStock(String symbol, int quantity) throws StockNotExistException,QuantityInvalidException{
		boolean sellStock = false;
		int stockSymbolIndex =0; 
		
		// the quantity is invalid
		if (quantity ==0 || quantity < -1 ){
			throw new QuantityInvalidException(quantity);
		}

		// find  stock's index to sell 
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocksStatus[i].getSymbol() ==  symbol){
				sellStock = true;
				stockSymbolIndex = i;
			}
		}
		// the symbol dosen't exists at stocks's array
		if (sellStock == false){
			throw new StockNotExistException();
		}
		// sell all quantity
		if (quantity == -1 || quantity == stocksStatus[stockSymbolIndex].stockQuantity){
			quantity = this.stocksStatus[stockSymbolIndex].stockQuantity;
		}	
		//the client asks to sell more stock than he have
		if (stocksStatus[stockSymbolIndex].stockQuantity < quantity){
			System.out.println("you ask to sell more stock then to actually have, all the quantity sell");
			quantity = this.stocksStatus[stockSymbolIndex].stockQuantity;
		}
		// Regular sale
		updateBalance(quantity * this.stocksStatus[stockSymbolIndex].bid);
		this.stocksStatus[stockSymbolIndex].stockQuantity -= quantity;
	}		

	/**
	 * Loop that contains the values of the stock portfolio   
	 * @return string whit stocks data
	 */
	public String getHtmlString(){
		String getHtmlString = "";
		getHtmlString += "<h1>" + this.title + "</h1>";
		for(int i = 0 ; i < portfolioSize ; i++){
			getHtmlString += stocksStatus[i].getHtmlDescription();			 
		}		
		getHtmlString += "<br>Total portfolio value: " +getStocksValue(stocksStatus)+ "$, Total Stocks value: "
				+getTotalValue(stocksStatus)+ "$, Balnce :"+ getBalance()+ "$ </br>" ;
		return getHtmlString;
	}
	/**
	 * string contains the values of the stock    
	 * @return string whit stocks data
	 */
	public float getStocksValue(StockStatus stockStatus[]) {
		float allStocksValue = 0;
		for (int i = 0; i < portfolioSize; i++) {
			allStocksValue += stockStatus[i].bid * stockStatus[i].stockQuantity;
		}
		return allStocksValue;
	}
	/**
	 * A method that returns how much money the customer have. 
	 *  ie, how much cash he holds (balance) and value of stocks that he has (stockStatus) 
	 * @return float 
	 */
	public float getTotalValue(StockStatus stockStatus[]){
		float totalValue = getStocksValue(stockStatus) + getBalance();
		return totalValue;
	}
	/**
	 * updates customer balance after purchase and sale of stocks
	 */
	public void updateBalance(float amount){
		this.balance += amount;
	}

	/**
	 * emum for recommendation status
	 */
	public enum  ALGO_RECOMMENDATION{
		DO_NOTHING(0),BUY(1),SELL(3);
		private int value;
		private ALGO_RECOMMENDATION(int value) {
			this.value = value;
		}
	}
	//getter and setter
	public float getBalance() {
		return balance;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	
	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}
	
	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
}