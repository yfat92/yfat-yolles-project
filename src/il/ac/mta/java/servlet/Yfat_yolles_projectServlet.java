package il.ac.mta.java.servlet;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Yfat_yolles_projectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html");
		
		//int num1;
		//int num2;
		//int num3;
		//int res;
		//num1 = 3;
		//num2 = 4;
		//num3 = 7;
		//res =  num3*(num1+num2);		
		//String resultStr = new String("<h1>Result of " +num3+ "* (" +num2+ "+" +num1+ " ) ="  +res+"</h1>");	
		
		double radius = 50;
		double areaCircle;
		areaCircle = Math.PI * (radius*radius);
		String line1 = new String("<h1>calculation 1: Area of circle with radius "+radius+" is" +areaCircle);
	
		int length = 50;
		int b = 30;
		double newb = Math.toRadians(b);
		float opposite = (float) (Math.sin(newb) * length) ;
		String line2 = new String("<h1>calculation 2: Length of opposite where angle B " +opposite);
		
		int base = 20;
		int exp = 13;
		double pow = Math.pow(base, exp);
		String line3 = new String("<h1>calculation 3: Power of 20 with exp of 13 is " +pow);
		
		//resp.getWriter().println(resultStr);
		resp.getWriter().println(line1);
		resp.getWriter().println(line2);
		resp.getWriter().println(line3);
	}

}
