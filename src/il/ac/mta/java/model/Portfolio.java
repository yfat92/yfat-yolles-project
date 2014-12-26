package il.ac.mta.java.model;

import java.util.Date;

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
	Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.balance = portfolio.getBalance();

		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for(i = 0; i < portfolioSize; i++)
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
			this.stocksStatus[i] = stockStatus[i];
		}
	}

	//methods
	/**
	*This method add stock to the array.
	*it's also do validation, if the client  
	*ask to add stock with a symbol that already exists
	*/
	public void addStock(Stock stock) {
		int countStockSymbol =0;

		// find the symbol's index and count 
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  stock.getSymbol()){
				countStockSymbol++;
			}
		}
		if (countStockSymbol == 1){
			System.out.println("“Can’t add stock, stock whit this symbol is already exists ");			
			return;
		}
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("“Can’t add new stock, portfolio can have only " +MAX_PORTFOLIO_SIZE+ " stocks");
			return;
		}

		stocks[portfolioSize] = stock;			
		stocksStatus[portfolioSize] = new Portfolio.StockStatus(stock.getSymbol()
				, stock.getBid(), stock.getAsk(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		portfolioSize++;
	}
	/**
	*This method remove stock from stock's array and sell 
	* all the stock's quantity
	* @return boolean if the remove succeed
	*/
	public boolean removeStock(String symbol ){		
		boolean stockIsExisist = false;		
		int StockSymbolIndex = 0;

		// find the index of symbol
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  symbol){
				stockIsExisist = true;
				StockSymbolIndex = i;
			}
		}

		// if stock symbol dosen't exists
		if(stockIsExisist == false){
			System.out.println("“Can’t remove stock, stock's name dosen't exists");			
			return stockIsExisist;
		}
		// if stock index is the last
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
	/**
	*This method buy stocks according to the quantity    
	*  
	* @return boolean if the buy succeed
	*/
	public boolean buyStock(String symbol, int quantity ){
		boolean buyStockSucsses = false;
		int stockSymbolIndex = 0;

		if (balance <=0){
			System.out.println("Can’t buy stock, your balance is: " +balance );		
			return buyStockSucsses;
		}
		// find the index of symbol
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  symbol){
				buyStockSucsses = true;
				stockSymbolIndex = i;
			}
		}
		// if the stock's name dosen't exists
		if(buyStockSucsses == false){
			System.out.println("Can’t buy stock, stock's name dosen't exists");			
			return buyStockSucsses;
		}
		if (quantity == -1 ){
			int numOfStocks = (int)(Math.floor((double)(balance/stocksStatus[stockSymbolIndex].currentAsk)));
			this.stocksStatus[stockSymbolIndex].stockQuantity += numOfStocks;
			updateBalance(-numOfStocks * stocksStatus[stockSymbolIndex].currentAsk);
			return buyStockSucsses;
		}

		updateBalance(-quantity * this.stocksStatus[stockSymbolIndex].currentAsk);
		this.stocksStatus[stockSymbolIndex].stockQuantity += quantity;
		return buyStockSucsses;

	}
	/**
	*This method sell stock and remove it from the array.
	*If the quantity is equal or bigger then stock's quantity.
	*it's also do validation, if the client add invalid quantity.
	*The method update costumer's balance and number of stocks that the customer-owned
	*@return whether the sale succeeded
	*/
	public boolean sellStock(String symbol, int quantity){
		boolean sellStock = false;
		int stockSymbolIndex =0; 
		
		// the quantity is invalid
		if (quantity ==0 || quantity < -1 ){
			System.out.println("Can’t sell stock, quantity is invalid");
			return sellStock;
		}

		// find  stock's index to sell 
		for(int i= 0 ; i < portfolioSize; i++){
			if (stocks[i].getSymbol() ==  symbol){
				sellStock = true;
				stockSymbolIndex = i;
			}
		}
		// the symbol dosen't exists at stocks's array
		if (sellStock == false){
			return sellStock; 
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
		updateBalance(quantity * this.stocksStatus[stockSymbolIndex].currentBid);
		this.stocksStatus[stockSymbolIndex].stockQuantity -= quantity;
	
		return sellStock;
	}		

	/**
	 * Loop that contains the values of the stock portfolio   
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
	/**
	 * string contains the values of the stock    
	 * @return string whit stocks data
	 */
	public float getStocksValue(StockStatus stockStatus[]) {
		float allStocksValue = 0;
		for (int i = 0; i < portfolioSize; i++) {
			allStocksValue += stockStatus[i].currentBid * stockStatus[i].stockQuantity;
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
	 *stock status information, copy constructor and
	 *constructor 
	 ** @author yfat yolles
	 * @since 3/12/2014
	 * date 25/12/2014
	 */	
	
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
