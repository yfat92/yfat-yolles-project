package il.ac.mta.java.model;

import java.util.Date;

/**
 * an arrays of stock and stocksStatus 
 * @author yfat yolles
 * @since 3/12/2014
 * date 3/12/2014
 */
public class Portfolio {
	//members
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private int portfolioSize = 0;
	int i =0;
	private 

	//array
	Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];


	//קונסטרקטור
	public Portfolio(String portfolioTitle, int portfolioSize, Stock stocks[],
			StockStatus stockStatus){
		setTitle(portfolioTitle);
		setPortfolioSize(portfolioSize);
		for (i =0 ; i < portfolioSize ; i++)
		{
			this.stocks[i] = stocks[i]; 
			this.stocksStatus[i] = stocksStatus[i]; 	
		}
		this.stockStatus = new StockStatus(stockStatus);
	}
	// קופי
	public Portfolio(Portfolio portfolio){
		setTitle(portfolio.getTitle());
		setPortfolioSize(portfolio.getPortfolioSize());		
		Stock[] tempStockArray = portfolio.getStocks();
		StockStatus[] tempStockStatusArray = portfolio.getStocksStatus();
		//stocksStatus = new StockStatus(portfolio.stocksStatus);
		for (i =0 ; i < portfolioSize ; i++)
		{
			stocks[i] = tempStockArray[i];
			stocksStatus[i]= tempStockStatusArray[i];						
		}
		
	}
	
	//getter and setter
	public Stock[] getStocks() {
		return stocks;
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

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	//methods
	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;	 
	}
	//remove stock
	public void removeStock(Stock stock[], int index) {
		stocks[index] = null;
			 
	}

	
	/**
	 * loop for all the stocks 
	 *
	 * @return string whit stocks data
	 */
	public String getHtmlString(){
		String getHtmlString = " <h1>Portfolio : </h1> ";

		for(int i = 0 ; i < portfolioSize ; i++)
			getHtmlString += stocks[i].getHtmlDescription() ;

		return getHtmlString;
	}
	/**
	 *stock status information, getter and setter for the future
	 ** @author yfat yolles
	 * @since 3/12/2014
	 * date 3/12/2014
	 */	
	//inner class
	public class StockStatus {

		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;

		//getter setter
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getCurrentBid() {
			return currentBid;
		}
		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}
		public float getCurrentAsk() {
			return currentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

	}
}
