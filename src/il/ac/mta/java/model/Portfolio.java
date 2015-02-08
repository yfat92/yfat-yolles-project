package il.ac.mta.java.model;

import java.util.Date;
import java.util.List;

import org.apache.tools.ant.types.Quantifier;

import il.ac.mta.exception.*;
import il.ac.mta.java.model.StockStatus;
import il.ac.mta.java.model.Stock;

/**
 * the class save portfolio data and also create a copy of Portfolio
 * 
 * @author Yfat Yolles
 * @param <getStockStocks>
 * @since 3/12/2014 date 13/12/2014
 */
public class Portfolio {
	// members
	public final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private int portfolioSize = 0;
	private float balance;


	// array
	//private StockStatus[] stocksStatus;

	StockStatus[] stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	// copy constructor
	public Portfolio(Portfolio portfolio) throws BalanceException {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		for (int i = 0; i < portfolioSize; i++) {
			this.stockStatus[i] = new StockStatus(getStocksStatus()[i]);
		}
		updateBalance(portfolio.getBalance());
	}

	// constructor
	public Portfolio(String title, int portfolioSize, Stock[] stocks,
			StockStatus[] stockStatus, float balance) {
		this.title = title;
		this.portfolioSize = portfolioSize;
		this.balance = balance;
		for (int i = 0; i < portfolioSize; i++) {
			this.stockStatus[i] = stockStatus[i];
		}
	}
	
	public Portfolio(List<StockStatus> stockStatusList) {
		this();
		  int arraySize = stockStatusList.size();
		  setPortfolioSize(arraySize);

		  if(stockStatusList.size() > MAX_PORTFOLIO_SIZE)
		   arraySize = MAX_PORTFOLIO_SIZE;
		
		for (int i =0; i < stockStatusList.size(); i++ ){
			this.stockStatus[i] = stockStatusList.get(i);
		}
	}

	public Portfolio() {
		portfolioSize = 0;
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		balance = 0;
	}

	// methods
	/**
	 * This method add stock to the array. it's also do validation, if the
	 * client ask to add stock with a symbol that already exists
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {
		boolean doesStockExists = false;

		for (int i = 0; i < portfolioSize; i++){
			if (stockStatus[i].getSymbol().equals(stock.getSymbol())){
				doesStockExists = true;
			} 
		}

		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}
		else if (doesStockExists == true){
			throw new StockAlreadyExistsException(stock.getSymbol());
		}
		else{
			stockStatus[portfolioSize] = new StockStatus(stock.getSymbol(), stock.getAsk(),stock.getBid(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			this.portfolioSize++;
		}
	}

	/**
	 * This method remove stock from stock's array and sell all the stock's
	 * quantity
	 * 
	 * @return boolean if the remove succeed
	 */
	public void removeStock(String symbol) throws StockNotExistsException,
			IllegalQuantityException, BalanceException {
		boolean stockIsExisist = false;
		int StockSymbolIndex = 0;

		// find the index of symbol
		for (int i = 0; i < portfolioSize; i++) {
			if(symbol.equals(this.stockStatus[i].getSymbol())) {
				stockIsExisist = true;
				StockSymbolIndex = i;
			}
		}

		// if stock symbol dosen't exists
		if (stockIsExisist == false) {
			throw new StockNotExistsException(symbol);
		}
		// if stock index is the last
		if (StockSymbolIndex == MAX_PORTFOLIO_SIZE - 1) {
			sellStock(symbol, stockStatus[StockSymbolIndex].getStockQuantity());
			portfolioSize--;
		}

		if (stockIsExisist == true) {
			sellStock (symbol, this.stockStatus[StockSymbolIndex].getStockQuantity());
			this.stockStatus[StockSymbolIndex] = stockStatus[portfolioSize-1];
			this.stockStatus[StockSymbolIndex] = stockStatus[portfolioSize-1];
			this.portfolioSize--;
		}
	}
	/**
	 * This method buy stocks according to the quantity
	 * 
	 * @return boolean if the buy succeed
	 */
	public void buyStock(String symbol, int quantity) throws BalanceException, StockNotExistsException, IllegalQuantityException
	{
		int maxQuantity; 
		int tQuantity;
		if(quantity < -1)
		{
			throw new IllegalQuantityException();
		}
		
		for(int i=0; i < portfolioSize; i++)
		{
			if(this.stockStatus != null && this.stockStatus[i].getSymbol().equalsIgnoreCase(symbol))
			{
				maxQuantity = (int)(balance / stockStatus[i].getAsk());
				tQuantity = quantity;
				if (quantity == -1)
				{
					tQuantity = maxQuantity;
				}
				else if (quantity > maxQuantity){
					throw new BalanceException();
				}
				
				updateBalance(-tQuantity * stockStatus[i].getAsk());
				stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+tQuantity);
				
				return;
			}
		}
		throw new StockNotExistsException(symbol);
	}


	/**
	 * This method sell stock and remove it from the array. If the quantity is
	 * equal or bigger then stock's quantity. it's also do validation, if the
	 * client add invalid quantity. The method update costumer's balance and
	 * number of stocks that the customer-owned
	 *
	 * @return whether the sale succeeded
	 */
	public void sellStock(String symbol, int quantity)
			throws StockNotExistsException, IllegalQuantityException, BalanceException {
		boolean sellStock = false;
		int stockSymbolIndex = 0;

		// the quantity is invalid
		if (quantity == 0 || quantity < -1) {
			throw new IllegalQuantityException();
		}

		// find stock's index to sell
		for (int i = 0; i < portfolioSize; i++) {
			if(symbol.equals(this.stockStatus[i].getSymbol())) {
				sellStock = true;
				stockSymbolIndex = i;
			}
		}
		// the symbol dosen't exists at stocks's array
		if (sellStock == false) {
			throw new StockNotExistsException(symbol);
		}
		// sell all quantity
		if (quantity == -1
				|| quantity == stockStatus[stockSymbolIndex].stockQuantity) {
			quantity = this.stockStatus[stockSymbolIndex].stockQuantity;
		}
		// the client asks to sell more stock than he have
		if (stockStatus[stockSymbolIndex].stockQuantity < quantity) {
			System.out
					.println("you ask to sell more stock then to actually have, all the quantity sell");
			quantity = this.stockStatus[stockSymbolIndex].stockQuantity;
		}
		// Regular sale
		updateBalance(quantity * this.stockStatus[stockSymbolIndex].bid);
		this.stockStatus[stockSymbolIndex].stockQuantity -= quantity;
	}

	/**
	 * Loop that contains the values of the stock portfolio
	 * 
	 * @return string whit stocks data
	 */
	public String getHtmlString() {
		String getHtmlString = "";
		getHtmlString += "<h1>" + this.title + "</h1>";
		for (int i = 0; i < portfolioSize; i++) {
			getHtmlString += stockStatus[i].getHtmlDescription();
		}
		getHtmlString += "<br>Total portfolio value: "
				+ getStocksValue(stockStatus) + "$, Total Stocks value: "
				+ getTotalValue(stockStatus) + "$, Balnce :" + getBalance()
				+ "$ </br>";
		return getHtmlString;
	}

	/**
	 * string contains the values of the stock
	 * 
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
	 * A method that returns how much money the customer have. ie, how much cash
	 * he holds (balance) and value of stocks that he has (stockStatus)
	 * 
	 * @return float
	 */
	public float getTotalValue(StockStatus stockStatus[]) {
		float totalValue = getStocksValue(stockStatus) + getBalance();
		return totalValue;
	}

	/**
	 * updates customer balance after purchase and sale of stocks
	 */
	public void updateBalance(float amount) throws BalanceException{
		if (balance + amount < 0){
			throw new BalanceException();
		}
		else
		{
			balance += amount;
		}
	}

	/**
	 * emum for recommendation status
	 */
	public enum ALGO_RECOMMENDATION {
		DO_NOTHING(0), BUY(1), SELL(3);
		private int value;

		private ALGO_RECOMMENDATION(int value) {
			this.value = value;
		}
	}

	// getter and setter
	public float getBalance() {
		return balance;
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

	public StockStatus[] getStocks() {
		return this.stockStatus;
	}

	public StockStatus[] getStocksStatus() {
		return stockStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stockStatus = stocksStatus;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	public StockStatus findBySymbol(String symbol)
			throws StockNotExistsException {
		for (int i = 0; i < portfolioSize; i++) {
			if (this.stockStatus[i] != null) {
				if (this.stockStatus[i].getSymbol().toLowerCase().equals(symbol))
					return this.stockStatus[i];
			}
		}
		throw new StockNotExistsException(symbol);
	}
}