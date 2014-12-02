package il.ac.mta.java.stocks.servlet;

import il.ac.mta.java.Stock;
import il.ac.mta.java.model.Portfolio;

public class PortfolioService {

	private void doGet() {
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();

	}

	private Portfolio getPortfolio() {
		// TODO Auto-generated method stub
		return null;
	}
}
