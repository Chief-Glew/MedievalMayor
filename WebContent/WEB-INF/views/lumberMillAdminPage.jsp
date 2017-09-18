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
	<nav class="navbar navbar-default">
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
					href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/LumberMill">Lumber
						Mill</a></li>
			</ul>
		</div>
	</nav>
			<h1>Admin</h1>

		<div class="info">
		<form>
		<table>
			<tr>
			<th><label>Set Base Resource Production: </label></th>
			<td><input type="number" value="<c:out value="${baseResourceProduction}"/>" min="0" required></td>
			</tr>
			<tr>
			<th><label>Set Upgrade Multiplier: </label></th>
			<td><input type="number" value="<c:out value="${upgradeMultiplier}"/>" min="0" required></td>
			</tr>
			<tr>
			<th><label>Set Amount Of Lumber Produced For Each Piece Of Wood: </label></th>
			<td><input type="number" value="<c:out value="${amountOfLumberPerWood}"/>" min="0" required></td>
			</tr>
			</table>
			<input class="btn btn-success" type="submit" value="submit">
			<input class="btn btn-danger" type="reset">
			<input class="btn btn-info" type="submit" value="back" formaction="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/admin" formmethod="get">
		</form>
	</div>
</body>
</html>