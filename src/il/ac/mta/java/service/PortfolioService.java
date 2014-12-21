package il.ac.mta.java.service;

import java.util.Calendar;
import java.util.Date;

import il.ac.mta.java.model.Portfolio;

import il.ac.mta.java.model.Stock;

/**
 *Portfolio's calculations 
 *initialize stock data, create stock instance
 *and add them to the array
 *return the Portfolio instance  
 ** @author yfat yolles
 * @since 3/12/2014
 * date 13/12/2014
 */	
public class PortfolioService {

	public Portfolio getPortfolio(){
		Portfolio myPortfilo = new Portfolio(" Exercise 7 portfilo", 0, null, null,0);
		Calendar c = Calendar.getInstance();
		c.set(2015, 11, 15);
		Date myDate = c.getTime();
		myPortfilo.updateBalance(10000);

		// constructors
		Stock pih = new Stock((float) 10, (float) 8.5, "pih", myDate);
		myPortfilo.addStock(pih);
		
	
		Stock all = new Stock((float) 30, (float) 25.5, "all", myDate);
		myPortfilo.addStock(all);


		Stock caas = new Stock((float) 20, (float) 15.5, "caas", myDate);
		myPortfilo.addStock(caas);

		
		myPortfilo.buyStock("pih", 20);
		myPortfilo.buyStock("all", 30);
		myPortfilo.buyStock("caas", 40);
		myPortfilo.sellStock("all", -1);	
		myPortfilo.removeStock("caas");
		return myPortfilo;
	}

}




