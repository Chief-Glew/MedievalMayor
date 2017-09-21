<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
body {
	background-image:
		url('hhttp://localhost:8088/medievalmayor/%3Cc:out%20value=ttps://i.ytimg.com/vi/UON27Kydqsw/maxresdefault.jpg');
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
	<h1>You Win</h1>
	<caption class="title">City Stats</caption>

	<table>
		<tr>
			<th>City Name</th>
			<td>${city.cityName}</td>
			<th>City Id</th>
			<td>${city.cityId}</td>
			<th>Total Popolation</th>
			<td>${city.totalPopulation}</td>
		</tr>
	</table>
</body>
</html>





