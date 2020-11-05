<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Eigenaar
  Date: 22/10/2020
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Contact Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><!--<span>Tracing App</span>--></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=Overview">Overview</a></li>
                <li id="actual"><a href="Controller?action=ContactOverview">Contacts</a></li>
                <li><a href="Controller?action=Register">Register</a></li>
            </ul>
        </nav>
        <h2>Contact Overview</h2>
    </header>
    <main>

        <C:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <C:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </C:forEach>
                </ul>
            </div>
        </C:if>

        <table>
            <tr>
                <th>Date</th>
                <th>Hour</th>
                <th>Name</th>
                <C:choose>
                    <C:when test="${showUniqueContacts eq 'no'}">
                        <th id="link"><a href="/Controller?action=ContactOverview&value=yes">Toon alle unieke contacten</a></th>
                    </C:when>
                    <C:otherwise>
                        <th id="link"><a href="/Controller?action=ContactOverview&value=no">Toon alle contacten</a></th>
                    </C:otherwise>
                </C:choose>
            </tr>

            <C:forEach var="contact" items="${contacts}">
                <fmt:parseDate value="${contact.date}" type="date" pattern="yyyy-MM-dd" var="parsedDate"/>
                <tr>
                    <td id="dateTime"><fmt:formatDate pattern="dd/MM/yyyy" value='${parsedDate}'/></td>
                    <td>${contact.hour}</td>
                    <td>${contact.firstName} ${contact.lastName}</td>
                </tr>
            </C:forEach>

            <caption>Contacts Overview</caption>
        </table>

        <form method="POST" action="Controller?action=AddContact" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p>
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" value="${firstNamePreviousValue}" required>
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" value="${lastNamePreviousValue}" required>
            </p>
            <p>
                <label for="date">Date</label>
                <input type="date" id="date" name="date" value="${datePreviousValue}" required>
            </p>
            <p>
                <label for="hour">Hour</label>
                <input type="time" id="hour" name="hour" value="${hourPreviousValue}" required>
            </p>
            <p>
                <label for="gsm">GSM</label>
                <input type="tel" id="gsm" name="gsm" value="${gsmPreviousValue}" required>
            </p>
            <p>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${emailPreviousValue}" required>
            </p>
            <p>
                <input type="submit" id="addContact" value="Add Contact">
            </p>

        </form>

    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>


</body>
</html>
