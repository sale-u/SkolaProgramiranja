<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Profesor"%>
<%@ page import="model.Predmet"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>profesor predmet</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>

<jsp:useBean id="profesor" scope="request" class="model.Profesor"></jsp:useBean>
<jsp:useBean id="sviPredmeti" scope="request" class="java.util.ArrayList"></jsp:useBean>

	<h1>SVE O PREDMETIMA PROFESORA</h1>
	<a href="PrikaziProfesoreController">back to TABELA PROFESORA</a> <br><br>
	
	<h2>Dobrodosli na stranicu profesora : ${profesor.firstName} ${profesor.lastName }</h2>
	
	<p>TABELA PREDMETA</p>
	<table border="1">
		<tr>
			<th>NAZIV PREDMETA</th>
			<th>SIFRA PREDMETA</th>
			<th>UKLONI PREDMET</th>
		</tr>
		
		<c:forEach var="p" items="${profesor.predmetiKojePredaje}">
			<tr>
				<td>${p.nazivPredmeta }</td>
				<td>${p.sifraPredmeta }</td>
				<td>
					<form action="OduzmiPredmetProfesoruController" method="get">
						<input type="hidden" name="idProfesor" value="${profesor.idUserDetails }">
						<input type="hidden" name="idPredmet" value="${p.idPredmet }">
						<input type="submit" value="UKLONI PREDMET">
					</form>	
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	DODAJ PREDMET <br>
	<form action="DodajPredmetProfesoruController" method="get">
		<input type="hidden" name="idProfesor" value="${profesor.idUserDetails }">
		<select name="idPredmet">
			<c:forEach var="pr" items="${sviPredmeti}">
				<option value = "${pr.idPredmet }">"${pr.nazivPredmeta }"</option>
			</c:forEach>
		</select>
		<input type="submit" value="DODAJ PREDMET PROFESORU">
	</form>
	

</body>
</html>