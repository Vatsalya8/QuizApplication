package com.quiz.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM questions";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Question> questions = new ArrayList<>();
            while (rs.next()) {
                questions.add(new Question(
                    rs.getString("question_text"),
                    new String[] {
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4")
                    },
                    rs.getInt("correct_option") - 1 // Convert 1-based to 0-based index
                ));
            }

            request.getSession().setAttribute("questions", questions);
            response.sendRedirect("quiz.jsp");
        } catch (Exception e) {
            throw new ServletException("Error fetching questions", e);
        }
    }
}
