<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Change Mail</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Tracing App</span>
        </h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=Overview">Overview</a></li>
                <li><a href="Controller?action=Register">Register</a></li>
            </ul>
        </nav>
        <h2>Change Mail</h2>

    </header>
    <main>
        <C:if test="${error ne null}">
            <div class="alert-danger">
                <ul>
                    <li>${error}</li>
                </ul>
            </div>
        </C:if>
        <form method="POST" action="/Controller?action=ChangeMail">
            <p><label for="newMail">New mail</label>
                <input type="text" id="newMail" name="newMail" required></p>
            <p><input type="hidden" id="userid" name="userid" value="${userid}"></p>
            <p><input type="submit" id="changeMail" value="Change mail"></p>
        </form>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>