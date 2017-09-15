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
	<div class="container-fluid"
		style="background-color: green; color: lightblue;">
		<div class="center-block"
			style="background-color: yellow; width: 250px;">
			<h1>Build your City!</h1>
		</div>
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
			<th>Number of People in the <c:out value="${worker.key}"/>:</th>
			<td><c:out value="${worker.value}" /></td>
			<td><a href=""></a></td>
		</tr>
		</c:forEach>
		<c:forEach items="${resources}" var="resource">
		<tr>
			<th>Amount of <c:out value="${resource.key}"/>:</th>
			<td><c:out value="${resource.value }"></c:out></td>
		</tr>
		</c:forEach>
		</table>
		<form action="./NextTurn" method="post" name="nextDay">
			<input type="submit" value="Next Year"
				style="background-color: red; color: black;">
		</form>
		<img
			src="https://cdn.thinglink.me/api/image/510436523527634945/1240/10/scaletowidth"
			alt="Village" />

	</div>
</body>
</html>