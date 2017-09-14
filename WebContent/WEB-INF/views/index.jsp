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
				<a class="navbar-brand" href="./">Medieval Mayor</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="./">Home</a></li>
				<li><a href="./userHome">City</a></li>
				<li><a href="./farmService">Farm</a></li>
				<li><a href="./mineService">Mine</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid" style="background-color: green; color: lightblue;">
		<div class="center-block"
			style="background-color: yellow; width: 500px;">
			<h1>Welcome to Medieval Mayor!</h1>
		</div>
		<img class="image" src="images/village.jpg" alt="Village">
	</div>
	<form action= "./home" method= "post">
	<div>
	<c:forEach items = "${cities}" var = "city">
		<input type="radio" name="cityId" value = "${city.cityId}"><label><c:out value="${city}" /></label>
	</c:forEach>
	</div>
	<input type="submit" value="select">
	<input type="submit" value = "newcity" formaction="./newCity" formmethod="get">
	</form>
</body>
</html>