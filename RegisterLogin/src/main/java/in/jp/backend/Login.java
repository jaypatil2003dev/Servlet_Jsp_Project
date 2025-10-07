package in.jp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginForm")
public class Login extends HttpServlet
{
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
     {
    	 PrintWriter out= resp.getWriter();
    	 
    	 String myemail= req.getParameter("email1");
    	 String mypass= req.getParameter("pass1");
    	 
    	 try
    	 {
    		 Class.forName("org.postgresql.Driver");
    		 Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yt_demo?user=postgres&password=root");
    		 
    		 PreparedStatement ps = con.prepareStatement("SELECT * FROM register WHERE email=? and password=?");
    		 ps.setString(1, myemail);
    		 ps.setString(2, mypass);
    		 
    		ResultSet rs= ps.executeQuery();
    		if(rs.next())
    		{
    			HttpSession session = req.getSession();
    			session.setAttribute("session_name", rs.getString("name"));
    			
    			RequestDispatcher rd= req.getRequestDispatcher("/profile.jsp");
    			rd.include(req, resp);
    		}
    		else
    		{
    			resp.setContentType("text/html");
    			out.print("<h3 style='color:red'>Email id and pass didnot match</h3>");
    			RequestDispatcher rd= req.getRequestDispatcher("/login.jsp");
    			rd.include(req, resp);
    		}
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    		 
    		 resp.setContentType("text/html");
 			out.print("<h3 style='color:red'>"+e.getMessage()+"</h3>");
 			RequestDispatcher rd= req.getRequestDispatcher("/login.jsp");
 			rd.include(req, resp);
    	 }
    	 
     }
}
