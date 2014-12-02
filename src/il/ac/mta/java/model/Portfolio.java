package il.ac.mta.java.model;

import java.util.Date;

public class Portfolio {

	private String title;
	final static int MAX_PORTFOLIO_SIZE = 5;
	static int portfolioSize = 0;
	
	Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	
	
	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;	 
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtmlString(){
		String getHtmlString = " <h1>Portfolio : </h1> ";

		for(int i = 0 ; i < portfolioSize ; i++)
			getHtmlString += stocks[i].getHtmlDescription() ;

		return getHtmlString;
	}

	public class StockStatus {
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		

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
