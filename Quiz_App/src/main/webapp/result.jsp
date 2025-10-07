<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = (String) session.getAttribute("username");
    Integer score = (Integer) session.getAttribute("score");
    if (name == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light text-center">

<div class="container mt-5">
    <div class="p-4 bg-white rounded shadow-sm">
        <h2>Congratulations, <%=name%> 🎉</h2>
        <h4>Your Score: <span class="text-success"><%=score%></span></h4>
        <div class="mt-4">
            <a href="quiz" class="btn btn-primary m-2">Retake Quiz</a>
            <a href="login.jsp" class="btn btn-secondary m-2">Logout</a>
        </div>
    </div>
</div>

</body>
</html>
