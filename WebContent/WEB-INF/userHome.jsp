<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body>
<p>number of unassigned people:<c:out value="${unnassignedPeople}"/></p>
<p>number of people in the farm:<c:out value="${farmers}"/><a href="./farmService">change</a></p>
<p>number of people in the mine:<c:out value="${miners}"/><a href="./mineService">change</a></p>
<p>ammount of food: <c:out value="${food }"></c:out></p>
<p>ammount of gold: <c:out value="${gold }"></c:out></p>
<form action="./userHome" method="post"><input type="submit" value="next day"></form>
</body>
</html>