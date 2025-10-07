package in.jp.quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			Connection con = DBConnect.getConn();
			PreparedStatement ps = con.prepareStatement("INSERT INTO USERS(name,email,password) VALUES (?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			int i = ps.executeUpdate();

			if (i > 0) {
				res.sendRedirect("login.jsp");
			} else {
				res.getWriter().println("Registration Failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
