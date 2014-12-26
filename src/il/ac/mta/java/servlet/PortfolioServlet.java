package il.ac.mta.java.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import il.ac.mta.java.model.Portfolio;
import il.ac.mta.java.model.Portfolio.StockStatus;
import il.ac.mta.java.model.Stock;
import il.ac.mta.java.service.PortfolioService;
/**
 *this class is print to html landing page 
 *and add a new instance of  PortfolioService
 ** @author yfat yolles
 * @since 3/12/2014
 * date 3/12/2014
 */	

public class PortfolioServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio1 = portfolioService.getPortfolio();
		Stock[] stocks = portfolio1.getStocks();
		StockStatus[] stocksStatus = portfolio1.getStocksStatus();
		resp.getWriter().println(portfolio1.getHtmlString());		
	}
}
