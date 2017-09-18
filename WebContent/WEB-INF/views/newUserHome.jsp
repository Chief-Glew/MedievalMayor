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
<style>
body {
	background-image:
		url('https://i.ytimg.com/vi/UON27Kydqsw/maxresdefault.jpg');
	background-size: 100%;
	max-width: 1500px;
	margin: auto;
}

.info {
	margin: 30px;
	background-color: #ffffff;
	border: 1px solid black;
	opacity: 0.6;
	filter: alpha(opacity = 60);
}
</style>
</head>
<body>
	<nav>
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/medievalmayor/">Medieval Mayor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/medievalmayor/">Home</a></li>
				<li class="active"><a
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>">City</a></li>
				<li><a
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/Farm">Farm</a></li>
				<li><a
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/Mine">Mine</a></li>
				<li><a
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/Forest">Forest</a></li>
				<li><a
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/LumberMill">Lumber Mill</a></li>
			</ul>
		</div>
	</nav>
	<h1>Build your City!</h1>
	<div class="info">
	<table>
		<tr>
			<th>Total Population:</th>
			<td><c:out value="${totalPopulation}" /></td>
		</tr>
		<tr>
			<th>Number of Unassigned People:</th>
			<td><c:out value="${unnassignedPeople}" /></td>
		</tr>
		<c:forEach items="${workers}" var="worker">
			<tr>
				<th>Number of People in the <c:out value="${worker.key}" />:
				</th>
				<td><c:out value="${worker.value}" /></td>
				<td><a class="btn btn-info"
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/<c:out value="${worker.key}" />">Change</a></td>
			</tr>
		</c:forEach>
		<c:forEach items="${resources}" var="resource">
			<tr>
				<th>Amount of <c:out value="${resource.key}" />:
				</th>
				<td><c:out value="${resource.value }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<form
		action="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/nextTurn"
		method="post" name="nextDay">
		<input type="submit" value="Next Year" class="btn btn-success">
	</form>
	<br>
	<a class="btn btn-danger"
		href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/admin">Go
		To Admin Page</a>
	<br>
	</div>
</body>
</html>