package il.ac.mta.java.model;

import java.util.Date;



/**
 * the class save portfolio data 
 *  and also create a copy of Portfolio 
 * @author yfat yolles
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
	Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.balance = portfolio.getBalance();

		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < portfolioSize; i++)
		{
			stocks[i] = new Stock(portfolio.stocks[i]);
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
			this.stocks[i] = stocks[i];
			this.stocksStatus[i] = stocksStatus[i];
		}
	}


	//methods
	public void addStock(Stock stock) {
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("“Can’t add new stock, portfolio can have only <PORTFOLIO_SIZE> stocks");

		}
		else{
			stocks[portfolioSize] = stock;			
			stocksStatus[portfolioSize] = new Portfolio.StockStatus(stock.getSymbol()
					, stock.getBid(), stock.getAsk(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			portfolioSize++;
		}
	}

	public boolean removeStock(String symbol ){		
		boolean stockIsExisist = false;
		int countStockSymbol =0;
		int StockSymbolIndex = 0;
		
		// find the index of symbol
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  symbol){
				stockIsExisist = true;
				countStockSymbol++;
				StockSymbolIndex = i;
			}
		}
			 
		// if stock symbol dosn't exisist
		if(stockIsExisist == false){
			System.out.println("“Can’t remove stock, stock's name dosen't exisist");			
			return stockIsExisist;
		}
		// there are more then one stock whit the same symbol
		if (countStockSymbol > 1){
			stockIsExisist = false;
			System.out.println("“Can’t remove stock, there are more then one stock whit this symbol");			
			return stockIsExisist;
		}
		if (StockSymbolIndex == MAX_PORTFOLIO_SIZE - 1){
			sellStock(symbol, stocksStatus[StockSymbolIndex].stockQuantity);
			portfolioSize--;
			return stockIsExisist;
		}
		if (stockIsExisist == true){
			sellStock(symbol, stocksStatus[StockSymbolIndex].stockQuantity);
			stocks[StockSymbolIndex] = stocks[portfolioSize-1];
			stocksStatus[StockSymbolIndex] = stocksStatus[portfolioSize-1];
			portfolioSize--;
			return stockIsExisist;
		}
		return stockIsExisist;
	}

	public boolean buyStock(String symbol, int quantity ){
		boolean buyStockSucsses = false;
		int countStockSymbol =0;
		int stockSymbolIndex = 0;
		
		if (balance <=0){
			System.out.println("Can’t buy stock, your balance is: " +balance );		
			return buyStockSucsses;
		}
		// find the index of symbol
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  symbol){
				buyStockSucsses = true;
				countStockSymbol++;
				stockSymbolIndex = i;
			}
		}
		// if the stock's name dosen't exisist
		if(buyStockSucsses == false){
			System.out.println("Can’t buy stock, stock's name dosen't exisist");			
			return buyStockSucsses;
		}
		// there are more then one stock whit this symbol
		if (countStockSymbol > 1){
			buyStockSucsses = false;
			System.out.println("“Can’t buy stock, there are more then one stock whit this symbol");			
			return buyStockSucsses;
		}
		if (quantity == -1 ){
			float stockPrice = stocksStatus[stockSymbolIndex].currentAsk;
			int numOfStock = (int)(double)(Math.floor(balance/stockPrice));
			balance -= numOfStock * stockPrice;
			stocksStatus[stockSymbolIndex].stockQuantity += numOfStock;
			return buyStockSucsses;
		}
		
		balance -= quantity * stocksStatus[stockSymbolIndex].currentAsk;
		stocksStatus[stockSymbolIndex].stockQuantity += quantity;
		return buyStockSucsses;

	}
	public boolean sellStock(String symbol, int quantity){
		boolean sellStock = false;
		int stockSymbolIndex =0; 
		
		if (quantity ==0 || quantity < -1 ){
			System.out.println("Can’t sell stock");
			return sellStock;
		}

		// find  stock's index to sell 
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  symbol){
				sellStock = true;
				stockSymbolIndex = i;
			}
		}
		if (quantity == -1 || quantity == stocksStatus[stockSymbolIndex].stockQuantity){
			System.out.println("you ask to sell all stock quantity");
			balance = stocksStatus[stockSymbolIndex].stockQuantity * stocksStatus[stockSymbolIndex].currentBid;
			quantity = stocksStatus[stockSymbolIndex].stockQuantity ;// update the quantity to maximum
			stocksStatus[stockSymbolIndex].stockQuantity -= quantity;
			return sellStock;
		}	
		
		// if the stocksStatus's quantity is 
		if (sellStock == true & stocksStatus[stockSymbolIndex].stockQuantity < quantity){
			balance = stocksStatus[stockSymbolIndex].stockQuantity * stocksStatus[stockSymbolIndex].currentBid;
			quantity = stocksStatus[stockSymbolIndex].stockQuantity ;// update the quantity to maximum
			stocksStatus[stockSymbolIndex].stockQuantity -= quantity; // update the quantity of stockQuantity
			System.out.println("you ask to sell more stock then to actualy have, all the quantity sell");
			return sellStock;
		}

		balance = stocksStatus[stockSymbolIndex].stockQuantity * stocksStatus[stockSymbolIndex].currentBid;
		stocksStatus[stockSymbolIndex].stockQuantity -= quantity;
		return sellStock;

	}		
	
	//getter and setter
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
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
	/**
	 * loop for all the stocks 
	 *
	 * @return string whit stocks data
	 */
	public String getHtmlString(){
		String getHtmlString = "";
		getHtmlString += "<h1>" + this.title + "</h1>";
		for(int i = 0 ; i < portfolioSize ; i++){
			getHtmlString += stocks[i].getHtmlDescription();			 
		}		
		getHtmlString += "<br>Total portfolio value: " +getStocksValue(stocksStatus)+ "$, Total Stocks value: "
				+getTotalValue(stocksStatus)+ "$, Balnce :"+ getBalance()+ "$ </br>" ;
		return getHtmlString;
	}
	public float getStocksValue(StockStatus stockStatus[]) {
		float allStocksValue = 0;
		for (int i = 0; i < portfolioSize; i++) {
			allStocksValue += stockStatus[i].currentBid * stockStatus[i].stockQuantity;
		}
		return allStocksValue;
	}
	public float getTotalValue(StockStatus stockStatus[]){
		float totalValue = getStocksValue(stockStatus) + getBalance();
		return totalValue;
	}
	//java doc
	public void updateBalance(float amount){
		this.balance = amount;
		if(amount < 0){
			System.out.println("the valaue is negative");
		}
	}

	//java doc ? 
	public enum  ALGO_RECOMMENDATION{
		DO_NOTHING(0),BUY(1),SELL(3);
		private int value;
		private ALGO_RECOMMENDATION(int value) {
			this.value = value;
		}
	}
	
	/**
	 *stock status information, copy constructor and
	 *constructor 
	 ** @author yfat yolles
	 * @since 3/12/2014
	 * date 13/12/2014
	 */	
	//inner class
	public class StockStatus {

		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
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
		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
		//constructor
		public StockStatus (String symbol, float currentBid, float currentAsk, Date date,
				ALGO_RECOMMENDATION recommendation, int stockQuantity){
			this.symbol = symbol;
			this.currentBid = currentBid;
			this.currentAsk = currentAsk;
			this.date = date;
			this.recommendation = recommendation;
			this.stockQuantity = stockQuantity;	
		}

		//copy constructor
		public StockStatus (StockStatus stockStatus){
			this.symbol = stockStatus.getSymbol();
			this.currentBid = stockStatus.getCurrentBid();
			this.currentAsk = stockStatus.getCurrentAsk();
			this.date = new Date(stockStatus.date.getTime());
			this.recommendation =stockStatus.getRecommendation();
			this.stockQuantity = stockStatus.getStockQuantity();
		}

	}
}
