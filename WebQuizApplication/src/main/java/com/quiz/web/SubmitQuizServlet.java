package com.quiz.web;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SubmitQuizServlet")
public class SubmitQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Question> questions = (ArrayList<Question>) request.getSession().getAttribute("questions");
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            String answer = request.getParameter("q" + i);
            if (answer != null && Integer.parseInt(answer) == questions.get(i).getCorrectAnswer()) {
                score++;
            }
        }

        request.getSession().setAttribute("score", score);
        response.sendRedirect("result.jsp");
    }
}
