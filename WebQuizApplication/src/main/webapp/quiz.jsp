<%@ page import="java.util.ArrayList" %>
<%@ page import="com.quiz.web.Question" %>
<%
    ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
    <script>
        let timeLeft = 60;
        function updateTimer() {
            if (timeLeft <= 0) {
                document.forms[0].submit();
            } else {
                document.getElementById("timer").innerText = "Time Left: " + timeLeft + " seconds";
                timeLeft--;
            }
        }
        setInterval(updateTimer, 1000);
    </script>
</head>
<body>
    <h1>Quiz</h1>
    <p id="timer">Time Left: 60 seconds</p>
    <form action="SubmitQuizServlet" method="post">
        <%
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
        %>
        <div>
            <p><%= "Q" + (i + 1) + ": " + q.getQuestionText() %></p>
            <%
                for (int j = 0; j < q.getOptions().length; j++) {
            %>
            <input type="radio" name="q<%= i %>" value="<%= j %>"> <%= q.getOptions()[j] %><br>
            <%
                }
            %>
        </div>
        <%
            }
        %>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
