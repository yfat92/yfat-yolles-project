package il.ac.mta.java.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PortfolioService;
import il.ac.mta.java.model.Portfolio;
import il.ac.mta.java.model.Stock;


public class PortfolioServlet  extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();
		
		resp.getWriter().println(portfolio.getHtmlString());
	}
}
