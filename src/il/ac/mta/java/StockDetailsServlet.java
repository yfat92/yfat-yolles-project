package il.ac.mta.java;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;

public class StockDetailsServlet  extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
			
			Calendar c = Calendar.getInstance();
			c.set(2015, 11, 15);
			Date myDate = c.getTime();
		
	        // constructors
				Stock pih = new Stock();
							
				pih.setAsk((float) 12.4);
				pih.setBid((float) 13.1);
				pih.setSymbol("PIH");
				pih.setDate(myDate);		
				
				resp.getWriter().println(pih.getHtmlDescription());
				Stock all = new Stock();
								
				all.setAsk((float) 5.5);
				all.setBid((float) 5.78);
				all.setSymbol("AAL");
				all.setDate(myDate);		
				resp.getWriter().println(all.getHtmlDescription());
				
				Stock caas = new Stock();
							
				caas.setAsk((float) 31.5);
				caas.setBid((float) 31.2);
				caas.setSymbol("CAAS");
				caas.setDate(myDate);		
				
				resp.getWriter().println(caas.getHtmlDescription());

	}
	      
}