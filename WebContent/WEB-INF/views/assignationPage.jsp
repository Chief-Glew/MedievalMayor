<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Farm Service</title>
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
			style="background-color: yellow; width: 415px;">
			<h1>Assign Workers To <c:out value="${producerName}"/>!</h1>
		</div>
		<p>
			Current Assigned Population:
			<c:out value="${currentAssigned}" />
		</p>
		<p>
			Max Assignable Population:
			<c:out value="${maxAssignable}" />
		</p>

		<form action="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/<c:out value="${producerName}"/>" method="post" name="assignFarmers">
			<label>New Population to set: </label> <input type="number"
				name="newAssignedPopulation" min="0"
				max="<c:out value="${maxAssignable}"/>"><br> <input
				type="submit" value="submit" style= "background-color: red; color: black;">
				<input
				type="submit" value="Back" formaction="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>" formmethod="get"
				style="background-color: red; color: white;">
		</form>
		<img
			src="https://i.pinimg.com/736x/45/13/ff/4513ffbecf17bcb4fd4008b7c2f32487.jpg"
			alt="Farm" />
	</div>
</body>
</html>