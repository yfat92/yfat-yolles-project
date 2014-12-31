package il.ac.mta.java.model;

import java.util.Date;

import il.ac.mta.java.model.Portfolio.ALGO_RECOMMENDATION;
import il.ac.mta.java.model.StockStatus;

/**
 *stock status information, copy constructor and
 *constructor 
 ** @author yfat yolles
 * @since 3/12/2014
 * date 25/12/2014
 */	

public class StockStatus extends Stock{

	private ALGO_RECOMMENDATION recommendation;
	int stockQuantity;
	
	//constructor
	public StockStatus(String symbol, float bid, float ask, Date date,
			ALGO_RECOMMENDATION recommendation, int stockQuantity){
		super(ask,bid,symbol,date);
		this.recommendation = recommendation;
		this.stockQuantity = stockQuantity;	
	}

	//copy constructor
	public StockStatus (StockStatus stockStatus){
		super(stockStatus);
		this.recommendation =stockStatus.getRecommendation();
		this.stockQuantity = stockStatus.getStockQuantity();
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
	
}
