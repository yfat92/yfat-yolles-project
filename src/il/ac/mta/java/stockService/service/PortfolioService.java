package il.ac.mta.java.stockService.service;

import java.util.Calendar;
import java.util.Date;

import il.ac.mta.java.Stock;
import il.ac.mta.java.model.Portfolio;

public class PortfolioService {
	
	public void getPortfolio(){
		Portfolio myPortfilo = new Portfolio();
		
		Calendar c = Calendar.getInstance();
		c.set(2015, 11, 15);
		Date myDate = c.getTime();
	
        // constructors
			Stock pih = new Stock();			
			pih.setAsk((float) 12.4);
			pih.setBid((float) 13.1);
			pih.setSymbol("PIH");
			pih.setDate(myDate);
			myPortfilo.addStock(pih);
		
			Stock all = new Stock();
			all.setAsk((float) 5.5);
			all.setBid((float) 5.78);
			all.setSymbol("AAL");
			all.setDate(myDate);
			myPortfilo.addStock(all);
			
			Stock caas = new Stock();	
			caas.setAsk((float) 31.5);
			caas.setBid((float) 31.2);
			caas.setSymbol("CAAS");
			caas.setDate(myDate);
			myPortfilo.addStock(caas);
			
	}
	


	
}
