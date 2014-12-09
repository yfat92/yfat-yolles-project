package il.ac.mta.java.model;

public class Stock {
	// members
	private float ask;
	private float bid;
	private String symbol;
	private java.util.Date date;
	
	public Stock(Stock st) {
		setAsk(st.getAsk());
		setBid(st.getBid());
		setSymbol(st.getSymbol());
		setDate(st.getDate());	
	}
	
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





