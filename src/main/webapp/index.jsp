<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1><!--<span>Tracing App</span>--></h1>
			<nav>
				<ul>
					<li id="actual"><a href="Controller">Home</a></li>
					<li><a href="Controller?action=Overview">Overview</a></li>
					<li><a href="Controller?action=Register">Register</a></li>
				</ul>
			</nav>
			<h2>Home</h2>

		</header>
		<main>
			<C:if test="${error ne null}">
				<div class="alert-danger">
					<ul>
						<li>${error}</li>
					</ul>
				</div>
			</C:if>

			<p>Welkom bij de contact trace applicatie van Scouting Lommel!</p>

			<C:choose>
				<C:when test="${personLogIn ne null}">
					<p>Welcome, ${personLogIn.firstName}</p>
					<form method="POST" action="/Controller?action=LogOut">
						<p><input type="submit" id="logOut" value="Log out"></p>
					</form>
				</C:when>
				<C:otherwise>
					<form method="POST" action="/Controller?action=LogIn">
						<p><label for="userid">User id</label>
							<input type="text" id="userid" name="userid" required>
						</p>
						<p><label for="password">Password</label>
							<input type="text" id="password" name="password">
						</p>
						<p><input type="submit" id="logIn" value="Log in"></p>
					</form>
				</C:otherwise>
			</C:choose>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>