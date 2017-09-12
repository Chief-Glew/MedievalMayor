<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Farm Service</title>
</head>
<body>

<p>Current Assigned Population: <c:out value="${currentAssigned}"/></p>
<p>Max Assignable Population: <c:out value="${maxAssignable}"/></p>

<form action="./farmService" method="post">
<label>New Population to set: </label>
<input type="number" name="newAssignedPopulation" min="0" max="<c:out value="${maxAssignable}"/>"><br>
<input type="submit" value="submit">
</form>

</body>
</html>