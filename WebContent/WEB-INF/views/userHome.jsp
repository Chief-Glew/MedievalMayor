<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
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
				<li><a href="./">Home</a></li>
				<li class="active"><a href="./userHome">City</a></li>
				<li><a href="./farmService">Farm</a></li>
				<li><a href="./mineService">Mine</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid" style="background-color: darkgreen;">
		<div class="center-block"
			style="background-color: yellow; width: 500px;">
			<h1>Build your City!</h1>
		</div>
		<p>
			Total Population:
			<c:out value="${totalPopulation}" />
		</p>
		<p>
			Number of Unassigned People:
			<c:out value="${unnassignedPeople}" />
		</p>
		<p>
			Number of People in the Farm:
			<c:out value="${farmers}" />
			<a href="./farmService">change</a>
		</p>
		<p>
			Number of People in the Mine:
			<c:out value="${miners}" />
			<a href="./mineService">change</a>
		</p>
		<p>
			Amount of food:
			<c:out value="${food }"></c:out>
		</p>
		<p>
			Amount of gold:
			<c:out value="${gold }"></c:out>
		</p>
		<form action="./userHome" method="post" name="nextDay">
			<input type="submit" value="next day">
		</form>
	</div>
</body>
</html>