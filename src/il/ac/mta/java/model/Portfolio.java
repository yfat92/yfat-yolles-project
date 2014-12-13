package il.ac.mta.java.model;

import java.util.Date;

/**
 * create a copy of Portfolio  
 * @author yfat yolles
 * @since 3/12/2014
 * date 13/12/2014
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

	//constructor
	public Portfolio(){
		this.title = title;
		this.portfolioSize = portfolioSize;
		for (int i = 0; i < portfolioSize; i++)
		{
			this.stocks[i] = stocks[i];
			this.stocksStatus[i] = stocksStatus[i];
		}
	}

	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();

		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < portfolioSize; i++)
		{
			stocks[i] = new Stock(portfolio.stocks[i]);
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
	 *stock status information, copy constructor and
	 *constructor 
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
		
		//constructor
		public StockStatus (String symbol, float currentBid, float currentAsk, Date date, int recommendation, int stockQuantity){
			this.symbol = getSymbol();
			this.currentBid = getCurrentBid();
			this.currentAsk = getCurrentAsk();
			this.date = getDate();
			this.recommendation = getRecommendation();
			this.stockQuantity = getStockQuantity();	
		}
		
		//copy constructor
		public StockStatus (StockStatus stockStatus){
			this.symbol = stockStatus.getSymbol();
			this.currentBid = stockStatus.getCurrentBid();
			this.currentAsk = stockStatus.getCurrentAsk();
			this.date = stockStatus.getDate();
			this.recommendation =stockStatus.getRecommendation();
			this.stockQuantity = stockStatus.getStockQuantity();
		}

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
