<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.Student" %>
<%@ page import = "model.Predmet" %>
<%@ page import = "model.User" %>
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
	<jsp:useBean id="user" scope="session" class="model.User"></jsp:useBean>
	
	<h1>Spisak studenta na smerovima na kojima se slusa odabran predmet / upis ocena</h1>
	<br>
	<a href = "ProfesorController?idUser=${user.idUser }">back to PROFESOROVI PREDMETI page</a>
	
	<br>
	<h4>Odabrani predmet : ${predmet.nazivPredmeta } / sifra predmeta : ${predmet.sifraPredmeta } </h4>
	
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
	
	<br><br>
	
	<p><b>UPISIVANJE OCENA:</b></p>
	<form action="UpisiOcenuController" method="get">
		<input type="hidden" name="idPredmet" value="${predmet.idPredmet }">
		<select name="studentId" >
			<c:forEach var="student" items="${sviStudenti }">
				<option value="${student.idUserDetails }"> ${student.indexNo } </option>
			</c:forEach>
		</select>
		<select name = "ocena">
			<c:forEach var = "o" begin="6" end="10">
				<option value = "${o }">${o }</option>
			</c:forEach>
		</select>
		<input type="submit" value="UPISI OCENU">
	</form>

</body>
</html>