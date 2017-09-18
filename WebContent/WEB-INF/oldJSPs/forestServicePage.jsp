<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forest Service</title>
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
				<li><a href="/medievalmayor/">Home</a></li>
				<li><a href="./userHome">City</a></li>
				<li><a href="/medievalmayor/farmService">Farm</a></li>
				<li><a href="/medievalmayor/mineService">Mine</a></li>
				<li class="active"><a href="/medievalmayor/forestService">Forest</a></li>
				<li><a href="/medievalmayor/LumberMillService">Lumber Mill</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid"
		style="background-color: green; color: lightblue;">

		<div class="center-block"
			style="background-color: yellow; width: 415px;">
			<h1>Assign Workers To Forest!</h1>
		</div>
		<p>
			Current Assigned Population:
			<c:out value="${currentAssigned}" />
		</p>
		<p>
			Max Assignable Population:
			<c:out value="${maxAssignable}" />
		</p>

		<form action="./forestService" method="post" name="assignLumberJacks">
			<label>New Population to set: </label> <input type="number"
				name="newAssignedPopulation" min="0"
				max="<c:out value="${maxAssignable}"/>"><br> <input
				type="submit" value="submit"
				style="background-color: red; color: black;"> <input
				type="submit" value="Back" formaction="./userHome" formmethod="get"
				style="background-color: red; color: white;">
		</form>
	</div>
</body>
</html>