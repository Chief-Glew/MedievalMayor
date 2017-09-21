<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>City Service</title>
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
html {width: 100%;}
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
	max-width: 450px;
	margin: 0 auto;
	text-align: justify;
	text-justify: inter-word;
	content: "";
	border: 15px solid #9999ff;
	border-bottom: 25px solid transparent;
	border-right: 25px solid transparent;
}

.text {
	margin: 30px;
	background-color: #ffffff;
	border: 1px solid black;
	opacity: 0.6;
	filter: alpha(opacity = 0);
}
h1{
color: #73020F
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
			</ul>
		</div>
	</nav>
	<h1><span class="label label-default">Welcome to Medieval Mayor!</span></h1>
	<div class="text">
		<font color="black">THE men of the Middle Ages neither expected
			nor praise 1 originality in their writers, and this, coupled with the
			natural preference of most men for well-worn paths, means that the
			greater part of the literature of the period falls into clearly
			defined classes. Of the sources most commonly used by historians,
			annals and chronicles, saints' lives and the lives of monarchs, all
			had their conventional forms, most of which can be traced back to
			prototypes in classical Latin literature or to the writings of the
			early Middle Ages. The survival of a number of literary descriptions
			of cities, the earliest belonging to the eighth century and the last
			examples to the seventeenth century or even later, therefore raises
			the question of whether these writings were linked by a tradition or
			traditions, and what their origin may have been. The first person to
			divine the potential significance of this class of literature seems
			to have been the great Muratori who, introducing his edition of a
			fourteenth- century description of the city of Pavia in 1727,
			expressed the wish that many more such descriptions might be made
			available to historians*; by the end of the nineteenth century this
			wish had been largely fulfilled and the chief examples of the genre
			had appeared in print. But the descriptiones as a class of literature
			had received very little attention; historians like Francesco Novati
			and L. A. Ferrai, who treated the subject briefly, assumed that they
			constituted some kind of literary tradition but made no real attempt
			to discover what its positive content might be. Moreover, they seem
			to have regarded the tradition as a purely Italian one and took no
			account of the appearance of similar writings in other countries,
			while in their conjectures concerning the chronology of the various
			Italian works they were seriously misled by misconceptions about the
			dating of the important corpus of material relating to the city of
			Rome. </font>
	</div>
	
	<h1><span class="label label-default">Select your City!</span></h1>
	<div class="info">
		<form action="/medievalmayor/home" method="get">
			<c:forEach items="${cities}" var="selectCity">
				<input type="submit" name="cityId" value="Select"
					formaction="/medievalmayor/<c:out value="${selectCity.cityName}"/>/<c:out value="${selectCity.cityId}"/>" />
				<label><c:out value="${selectCity.cityId}" />:<c:out
						value="${selectCity.cityName}" /></label>
				<br>
			</c:forEach>
		</form>
		<form>
			<input type="text" value="Rivendale" name="cityName"
				pattern="[a-zA-Z0-9\s]+"  maxlength="28" required> <input type="submit"
				value="New City" formaction="/medievalmayor/newCity" formmethod="get">
		</form>
	</div>
</body>
</html>