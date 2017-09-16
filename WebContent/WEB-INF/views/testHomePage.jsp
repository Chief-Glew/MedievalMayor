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
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/medievalmayor/">Medieval Mayor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/medievalmayor/">Home</a></li>
				<li class="active"><a href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>">City</a></li>
				<li><a href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/Farm">Farm</a></li>
				<li><a href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/Mine">Mine</a></li>
				<li><a href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/Forest">Forest</a></li>
				<li><a href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/LumberMill">Lumber Mill</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid" style="background-color: green; color: lightblue;">
		<div class="center-block"
			style="background-color: yellow; width: 500px;">
			<h1>Welcome to Medieval Mayor!</h1>
		</div>
		<img src="https://cdn.thinglink.me/api/image/510436523527634945/1240/10/scaletowidth" alt="Village" />
	</div>
	<form action= "/medievalmayor/" method= "post">
	<div>
	<c:forEach items = "${cities}" var = "selectCity">
		<input type="radio" name="cityId" value = "${selectCity.cityId}"><label><c:out value="${selectCity.cityId}" />:<c:out value="${selectCity.cityName}" /></label><br>
	</c:forEach>
	</div>
	<input type="submit" value="select">
	<input type="submit" value = "newcity" formaction="/medievalmayor/newCity" formmethod="get">
	</form>
</body>
</html>