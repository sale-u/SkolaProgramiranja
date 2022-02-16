<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.Student" %>
<%@ page import = "model.Predmet" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>spisak studenata na smerovima na kojima je odabran predmet</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "css/style.css"
	/>
</head>
<body>
	<jsp:useBean id="predmet" scope="request" class="model.Predmet"></jsp:useBean>
	<jsp:useBean id="sviStudenti" scope="request" class="java.util.ArrayList"></jsp:useBean>
	
	<h1>Spisak studenta na smerovima na kojima se slusa odabran predmet</h1>
	<br>
	<a href = "ProfesorController">back to PROFESOROVI PREDMETI page</a>
	
	<br>
	<h4>Odabrani predmet : ${predmet.nazivPredmeta }</h4>
	
	<table border="1">
		<tr>
			<th>BROJ INDEXA</th>
			<th>IME STUDENTA</th>
			<th>PREZIME STUDENTA</th>
		</tr>
		<c:forEach var="s" items = "${sviStudenti }">
			<tr>
				<td>${s.indexNo }</td>
				<td>${s.firstName }</td>
				<td>${s.lastName }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>