package in.jp.quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			Connection con = DBConnect.getConn();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("userId", rs.getInt("id"));
				session.setAttribute("username", rs.getString("name"));
				res.sendRedirect("quiz");
			} else {
				res.getWriter().println("Invalid email or password!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
