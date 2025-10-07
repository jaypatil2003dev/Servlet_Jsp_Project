<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="text-center bg-light">
    <div class="container mt-5">
        <h3 class="text-danger">Oops! Something went wrong </h3>
        <p><%= exception.getMessage() %></p>
        <a href="index.jsp" class="btn btn-primary mt-3">Go Home</a>
    </div>
</body>
</html>
