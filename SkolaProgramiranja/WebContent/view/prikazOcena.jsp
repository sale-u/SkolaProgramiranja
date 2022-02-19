<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.PolozeniIspiti" %>
<%@ page import = "java.lang.String" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ocene studenata na predmetu</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "css/style.css"
	/>
</head>
<body>

	<jsp:useBean id="polozeniIspiti" scope="request" class="java.util.ArrayList"></jsp:useBean>
	<jsp:useBean id="idPredmet" scope="request" class="java.lang.String"></jsp:useBean>

	<h1>OCENE</h1>
	
	<br>
	<a href = "SpisakStudenataController?idPredmeta=${idPredmet }">back to SPISAK STUDENATA NA PREDMETU page</a>
	<br><br>
	
	<table border=1>
		<tr>
			<th>BROJ INDEXA</th>
			<th>IME STUDENTA</th>
			<th>PREZIME STUDENTA</th>
			<th>PREDMET</th>
			<th>SIFRA PREDMETA</th>
			<th>OCENA</th>
			<th>DATUM UPISA</th>
		</tr>
		
		<c:forEach var= "pi" items="${polozeniIspiti }">
			<tr>
				<td>${pi.student.indexNo }</td>
				<td>${pi.student.firstName }</td>
				<td>${pi.student.lastName }</td>
				<td>${pi.predmet.nazivPredmeta }</td>
				<td>${pi.predmet.sifraPredmeta }</td>
				<td>${pi.ocena }</td>
				<td>${pi.datumPolaganja }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>