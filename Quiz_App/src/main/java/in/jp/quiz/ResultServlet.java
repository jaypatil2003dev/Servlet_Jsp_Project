package in.jp.quiz;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("username");
        int score = (int) session.getAttribute("score");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2>Hi, " + name + "</h2>");
        out.println("<h3>Your Score: " + score + "</h3>");
        out.println("<a href='quiz'>Take Quiz Again</a>");
    }
}
