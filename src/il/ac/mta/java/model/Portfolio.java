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
	
	

	public class StockStatus {
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
				
		
	}









}
