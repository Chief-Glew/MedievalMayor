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
	max-width: 350px;
	margin: 0 auto;
	text-align: justify;
	text-justify: inter-word;
	content: "";
	border: 15px solid #9999ff;
	border-bottom: 25px solid transparent;
	border-right: 25px solid transparent;
}

th, td {
	border-bottom: 1px solid #ddd;
}

tr:hover {
	background-color: #f5f5f5;
}

h1 {
	color: #73020F;
}

.title {
	text-align: left;
	margin-bottom: 5px;
	font-size: 160%;
	padding: 5px;
	font-weight: bold;
	color: #73020F;
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
				<c:forEach items="${resourceProducers}" var="resourceProducer">
					<li><a
						href="/medievalmayor/<c:out value="${city.cityName}"/>/<c:out value="${city.cityId}"/>/<c:out value="${resourceProducer.resourceProducerName}" />"><c:out
								value="${resourceProducer.resourceProducerName}" /></a></li>
				</c:forEach>
			</ul>
		</div>
	</nav>

	<h1>
		<span class="label label-default">Insufficient Resources To
			Upgrade <c:out value="${resourceProducerName}" />!
		</span>
	</h1>
	<div class="info">
	You do not have enough resources to upgrade ${resourceProducerName}.
	
	</div>
</body>
</html>