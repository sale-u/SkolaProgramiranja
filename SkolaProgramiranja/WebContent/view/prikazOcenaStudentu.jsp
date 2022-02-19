<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.PolozeniIspiti" %>
<%@ page import = "model.UserDetails" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>prikazi odredjenom studentu njegove ocene</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "css/style.css"
	/>
</head>

<body>
	<jsp:useBean id="polozeniIspiti" scope="request" class="java.util.ArrayList"></jsp:useBean>
	<jsp:useBean id="details" scope="request" class="model.UserDetails"></jsp:useBean>
	
		<h1>OCENE za odredjenog studenta</h1>
		
		<h4>Student : ${details.firstName } ${details.lastName }</h4>

<br>
	<a href = "view/student.jsp">back to STUDENT page</a>
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