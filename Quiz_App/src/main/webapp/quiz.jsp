<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Quiz</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .quiz-container {
            max-width: 800px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
        }
        .timer {
            float: right;
            font-weight: bold;
            font-size: 18px;
            color: #d9534f;
        }
        .question {
            margin-bottom: 25px;
        }
    </style>
</head>
<body>
<div class="quiz-container">
    <h2 class="text-center">Online Quiz</h2>
    <div class="timer">
        ⏱ Time Left: <span id="time">02:00</span>
    </div>
    <hr>

    <form action="submit" method="post" id="quizForm">
        <%
            List<Map<String,String>> questions = (List<Map<String,String>>) request.getAttribute("questions");
            int qNo = 1;
            for (Map<String,String> q : questions) {
        %>
        <div class="question">
            <p><b>Q<%= qNo++ %>:</b> <%= q.get("question") %></p>
            <div>
                <input type="radio" name="q<%= q.get("id") %>" value="<%= q.get("o1") %>"> <%= q.get("o1") %><br>
                <input type="radio" name="q<%= q.get("id") %>" value="<%= q.get("o2") %>"> <%= q.get("o2") %><br>
                <input type="radio" name="q<%= q.get("id") %>" value="<%= q.get("o3") %>"> <%= q.get("o3") %><br>
                <input type="radio" name="q<%= q.get("id") %>" value="<%= q.get("o4") %>"> <%= q.get("o4") %><br>
            </div>
        </div>
        <% } %>

        <div class="text-center">
            <button type="submit" class="btn btn-success">Submit Quiz</button>
        </div>
    </form>
</div>

<script>
    // Timer Duration (in seconds)
    let totalTime = 60; // 2 minutes

    function startTimer() {
        const timerDisplay = document.getElementById("time");
        const form = document.getElementById("quizForm");

        const timer = setInterval(() => {
            let minutes = Math.floor(totalTime / 60);
            let seconds = totalTime % 60;
            timerDisplay.textContent = 
                `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;

            totalTime--;

            if (totalTime < 0) {
                clearInterval(timer);
                alert("⏰ Time’s up! Submitting your quiz...");
                form.submit(); // Auto-submit when time runs out
            }
        }, 1000);
    }

    window.onload = startTimer;
</script>

</body>
</html>
