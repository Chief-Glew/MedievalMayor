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
	max-width: 1000px;
	margin: 0 auto;
	text-align: center;
}

.info {
	margin: 30px;
	background-color: #e6e6ff;
	border: 1px solid black;
	opacity: 0.9;
	filter: alpha(opacity = 0);
	max-width: 300px;
	margin: 0 auto;
	text-align: justify;
	text-justify: inter-word;
	content: "";
	border: 50px solid #9999ff;
	border-bottom: 50px solid transparent;
	border-right: 50px solid transparent;
}

th, td {
	border-bottom: 1px solid #ddd;
}

tr:hover {
	background-color: #f5f5f5
}

h1 {
	color: #73020F
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
		<form
			action="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/admin/<c:out value="${resourceProducer.resourceProducerName}"/>"
			method="post" name="setAdminModifiers">
			>
			<table>
				<tr>
					<th><label>Set Base Resource Production: </label></th>
					<td><input type="number" name="baseProduction"
						value="<c:out value="${baseResourceProduction}"/>" min="1"
						required></td>
				</tr>
				<tr>
					<th><label>Set Amount Of Lumber Produced For Each
							Piece Of Wood: </label></th>
					<td><input type="number" name="conversionFactor"
						value="<c:out value="${amountOfLumberPerWood}"/>" min="1" required></td>
				</tr>
				<tr>
					<th><label>Set Upgrade Multiplier: </label></th>
					<td><input type="number" name="upgradeMultiplier"
						value="<c:out value="${upgradeMultiplier}"/>" min="1" required></td>
				</tr>
			</table>
			<input class="btn btn-success" type="submit" value="submit">
			<input class="btn btn-danger" type="reset"> <input
				class="btn btn-info" type="submit" value="back"
				formaction="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/admin"
				formmethod="get">
		</form>
	</div>
</body>
</html>