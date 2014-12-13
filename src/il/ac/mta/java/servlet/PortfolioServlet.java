package il.ac.mta.java.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import il.ac.mta.java.model.Portfolio;
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
		
		//create a new portfolio and change the title 
		Portfolio portfolio2 = new Portfolio(portfolio1);
		portfolio2.setTitle("<h1><br>Portfolio#2</h1>");
		
		 
		resp.getWriter().println("<h1>Before the change :</h1>");	
		resp.getWriter().println(portfolio1.getHtmlString());// print portfolio1
		resp.getWriter().println(portfolio2.getHtmlString());// print portfolio2
		
		// remove first stock from portfolio1
		portfolio1.removeStock(stocks);
		resp.getWriter().println("<h1>Remove first stock from portfolio1</h1>");
		resp.getWriter().println(portfolio1.getHtmlString());// print portfolio1 	
		resp.getWriter().println(portfolio2.getHtmlString());// print portfolio2
		
		//change last stock's bid value
		portfolio2.getStocks()[2].setBid((float) 55.5);
		resp.getWriter().println("<h1>Change last stock's bid value to 55.5  at portfolio2</h1>");
		resp.getWriter().println(portfolio1.getHtmlString());// print portfolio1 	
		resp.getWriter().println(portfolio2.getHtmlString());// print portfolio2
	}
}
