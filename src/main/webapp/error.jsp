<%--
  Created by IntelliJ IDEA.
  User: Eigenaar
  Date: 19/10/2020
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Something Wrong</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <main id="container">
        <p>You caused a ${pageContext.exception} on the server.</p>
        <p>Go <a href="index.jsp">Home</a>.</p>
    </main>
</body>
</html>
