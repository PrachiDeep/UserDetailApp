package com.prachi.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */

//established connection using web.xml and init-params
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
   public void init(ServletConfig config)
   {
	   try {
//		   ServletContext context = config.getServletContext();
//	   System.out.println("Inside the init() method");
//		   Enumeration<String> names = context.getInitParameterNames();
//		   while(names.hasMoreElements())
//		   {
//			   String eachName = names.nextElement();
//			   System.out.println("Parameter name"+eachName);
//			   System.out.println("Parameter value"+context.getInitParameter(eachName)); 
//		   }
		   Class.forName("com.mysql.cj.jdbc.Driver");
		 connection = DriverManager.getConnection(config.getInitParameter("dbUrl"), config.getInitParameter("dbName"),config.getInitParameter("dbPassword"));
	} catch (SQLException e) {
		
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}  
   }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from user");
			PrintWriter out = response.getWriter();
			out.print("<HTML>");
			out.print("<BODY>");
			out.print("<TABLE>");
			
			out.print("<TR>");
			
			out.print("<TH>");
			out.print("FIRST NAME");
			out.print("</TH>");
			
			out.print("<TH>");
			out.print("LAST NAME");
			out.print("</TH>");
			
			out.print("<TH>");
			out.print("EMAIL");
			out.print("</TH>");
			
			out.print("</TR>");
			
			while(result.next())
			{
				out.print("<TR>");
				
				out.print("<TD>");
				out.print( result.getString(1));
				out.print("</TD>");
				
				out.print("<TD>");
				out.print( result.getString(2));
				out.print("</TD>");
				
				out.print("<TD>");
				out.print( result.getString(3));
				out.print("</TD>");
				
				out.print("</TR>");
				
			}
			
			out.print("</TABLE>");
			out.print("</BODY>");
			out.print("</HTML>");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void destroy()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
