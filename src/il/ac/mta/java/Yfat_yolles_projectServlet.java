package il.ac.mta.java;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Yfat_yolles_projectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html");
		int num1;
		int num2;
		int num3;
		int res;
		num1 = 3;
		num2 = 4;
		num3 = 7;
		res =  num3*(num1+num2);		
		String resultStr = new String("<h1>Result of " +num3+ "* (" +num2+ "+" +num1+ " ) ="  +res+"</h1>");	
		
		resp.getWriter().println(resultStr);
		// stam

	}

}
