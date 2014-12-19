package il.ac.mta.java.model;

import java.util.Date;

/**
 * this class have save stock's information
 * create a copy of Stock   
 * @author yfat yolles
 * @since 3/12/2014
 * date 13/12/2014
 */
public class Stock {
	// members
	private float ask;
	private float bid;
	private String symbol;
	private java.util.Date date;

	//copy constructor
	public Stock(Stock stock) {
		setAsk(stock.getAsk());
		setBid(stock.getBid());
		setSymbol(stock.getSymbol());
		this.date = new Date(stock.date.getTime());
	}
	//constructor
	public Stock(float ask, float bid, String symbol, java.util.Date date)	{
		this.ask = ask;
		this.bid = bid;
		this.symbol = symbol;
		this.date = date;	
	}


	public void setAsk(float ask){
		this.ask=ask;
	}
	public void setBid(float bid){
		this.bid=bid;
	}
	public void setSymbol(String symbol){
		this.symbol=symbol;
	}
	public void setDate(java.util.Date date){
		this.date=date;
	}
	public float getAsk(){
		return ask;
	}
	public float getBid(){
		return bid;
	}
	public String getSymbol(){
		return symbol;
	}
	public java.util.Date getDate(){
		return date;
	}

	public String getHtmlDescription(){

		return "<br><b>Symbol: </b>" + getSymbol() + ", <b>ask: </b>" + getAsk() + "<b>, bid: </b>" + getBid() + "<b> , date:  </b>" + getDate();
	}
}





