package il.ac.mta.java;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Stock  extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
			}
		
	// members
	        private float ask;
	        private float bid;
	        private String symbol;
	        private java.util.Date date;
	        // constructors
	        
	        public Stock(float ask, float bid, String symbol, java.util.Date date)
	        {
	        	this.ask=ask;
	        	this.bid=bid;
	        	this.symbol=symbol;
	        	this.date=date;
	        }
	        // functions
	        
	        public void setAsk(float ask){
	        	this.ask=ask;
	        }
	        public void setBid(float bid){
	        	this.bid=bid;
	        }
	        public void setSymbol(String symbol){
	        	this.symbol=symbol;
	        }
	        public void setDate(java.util.Date date){
	        	this.date=date;
	        }
	       public float getAsk(){
	    	   return ask;
	       }
	       public float getBid(){
	    	   return bid;
	       }
	       public String getSymbol(){
	    	   return symbol;
	       }
	       public java.util.Date getDate(){
	    	   return date;
	       }
	       public String getHtmlDescription(){
	    	   return "Symbol: " + symbol + ", ask: " + ask + ", bid: " + bid + " , date:  " + date;
	       }     
	       
	}