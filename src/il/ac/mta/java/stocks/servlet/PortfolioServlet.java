package il.ac.mta.java.stocks.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.mta.java.model.Portfolio;
import il.ac.mta.java.model.Stock;
import il.ac.mta.java.stockService.service.PortfolioService;



public class PortfolioServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();
		
		resp.getWriter().println(stocks[0].getHtmlString());
		resp.getWriter().println(stocks[1].getHtmlString());
		resp.getWriter().println(stocks[2].getHtmlString());
	}


}
