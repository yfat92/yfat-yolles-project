package il.ac.mta.java.service;

import java.util.Calendar;
import java.util.Date;

import il.ac.mta.java.model.Portfolio;
import il.ac.mta.java.model.Stock;
import il.ac.mta.java.servlet.PortfolioServlet;;
/**
 *instance Portfolio
 *initialize stock data, create stock instance
 *and add them to the array
 *return the Portfolio instance  
 ** @author yfat yolles
 * @since 3/12/2014
 * date 3/12/2014
 */	
public class PortfolioService {

	public Portfolio getPortfolio(){
		Portfolio myPortfilo = new Portfolio("Portfilo#1", 0, null, null);
		Calendar c = Calendar.getInstance();
		c.set(2015, 11, 15);
		Date myDate = c.getTime();

		// constructors
		Stock pih = new Stock((float) 12.4, (float) 13.1, "PIH", myDate);			
		myPortfilo.addStock(pih);

		Stock all = new Stock((float) 5.5, (float) 5.78, "AAL", myDate);
		myPortfilo.addStock(all);

		Stock caas = new Stock((float) 31.5, (float) 31.2, "CAAS", myDate);	
		myPortfilo.addStock(caas);
		return myPortfilo;
	}

}




