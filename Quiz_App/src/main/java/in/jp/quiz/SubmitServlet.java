package in.jp.quiz;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        int score = 0;

        try {
            Connection con = DBConnect.getConn();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions");

            while (rs.next()) {
                String qid = String.valueOf(rs.getInt("id"));
                String correct = rs.getString("correct_answer");
                String selected = req.getParameter("q" + qid);
                if (selected != null && selected.equals(correct)) {
                    score++;
                }
            }

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO results(user_id, score) VALUES (?,?)"
            );
            ps.setInt(1, userId);
            ps.setInt(2, score);
            ps.executeUpdate();

            session.setAttribute("score", score);
            res.sendRedirect("result");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
