package in.jp.quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            // Get DB connection
            Connection con = DBConnect.getConn();

            // Fetch 5 random questions from PostgreSQL
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions ORDER BY RANDOM() LIMIT 5");

            // Store all questions in a list of maps
            List<Map<String, String>> questions = new ArrayList<>();

            while (rs.next()) {
                Map<String, String> q = new HashMap<>();
                q.put("id", String.valueOf(rs.getInt("id")));
                q.put("question", rs.getString("question"));
                q.put("o1", rs.getString("option1"));
                q.put("o2", rs.getString("option2"));
                q.put("o3", rs.getString("option3"));
                q.put("o4", rs.getString("option4"));
                questions.add(q);
            }

            // Attach the list to the request and forward to quiz.jsp
            req.setAttribute("questions", questions);
            RequestDispatcher rd = req.getRequestDispatcher("quiz.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.setContentType("text/html");
            res.getWriter().println("<h3 style='color:red;'>Error loading quiz questions.</h3>");
        }
    }

    // Optional: handle POST same as GET (avoids 405 errors)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
