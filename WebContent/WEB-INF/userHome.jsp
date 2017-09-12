<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="./userHome">Medieval Mayor</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="./userHome">Home</a></li>
      <li><a href="./farmService">Farm</a></li>
      <li><a href="./mineService">Mine</a></li>
    </ul>
  </div>
</nav>
<p>number of unassigned people:<c:out value="${unnassignedPeople}"/></p>
<p>number of people in the farm:<c:out value="${farmers}"/><a href="./farmService">change</a></p>
<p>number of people in the mine:<c:out value="${miners}"/><a href="./mineService">change</a></p>
<p>ammount of food: <c:out value="${food }"></c:out></p>
<p>ammount of gold: <c:out value="${gold }"></c:out></p>
<form action="./userHome" method="post"><input type="submit" value="next day"></form>
</body>
</html>