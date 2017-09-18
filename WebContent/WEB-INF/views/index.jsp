<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Medieval Mayor!</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<nav>
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/medievalmayor/">Medieval Mayor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/medievalmayor/">Home</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid" style="background-color: green; color: lightblue;">
		<div class="center-block"
			style="background-color: yellow; width: 500px;">
			<h1>Welcome to Medieval Mayor!</h1>
		</div>
		<img src="https://i.pinimg.com/originals/8a/03/5f/8a035f25eaff8067d722f7a268f715cf.jpg" alt="Village" />
	</div>
	<form action= "/medievalmayor/home" method= "get">
	<div>
	<c:forEach items = "${cities}" var = "selectCity">
		<input type="submit" name="cityId" value = "select" formaction="/medievalmayor/<c:out value="${selectCity.cityName}"/>/<c:out value="${selectCity.cityId}"/>"/>
		<label><c:out value="${selectCity.cityId}" />:<c:out value="${selectCity.cityName}" /></label><br>
	</c:forEach>
	</div>
	</form>
	<form>
	<input type="text" value="DefaultCity" name="cityName" pattern="[^/]*">
	<input type="submit" value = "newcity" formaction="/medievalmayor/newCity" formmethod="get">
	</form>
</body>
</html>