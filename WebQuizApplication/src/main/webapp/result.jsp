<%@ page import="java.util.ArrayList" %>
<%@ page import="com.quiz.web.Question" %>
<%
    ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
    int score = (int) session.getAttribute("score");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Results</title>
</head>
<body>
    <h1>Results</h1>
    <p>Your Score: <%= score %> / <%= questions.size() %></p>
    <%
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
    %>
    <div>
        <p><strong>Q<%= i + 1 %>: <%= q.getQuestionText() %></strong></p>
        <p>Correct Answer: <%= q.getOptions()[q.getCorrectAnswer()] %></p>
    </div>
    <%
        }
    %>
    <a href="index.jsp">Take Quiz Again</a>
</body>
</html>
