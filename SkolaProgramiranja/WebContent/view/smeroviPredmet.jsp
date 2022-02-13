<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Predmet"%>
<%@ page import="model.Smer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>smerovi i predmeti</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

<body>
	<jsp:useBean id="sviPredmeti" scope="request"
		class="java.util.ArrayList"></jsp:useBean>
	<jsp:useBean id="sviSmerovi" scope="request"
		class="java.util.ArrayList"></jsp:useBean>
	<h1>PREDMETI i SMEROVI</h1>
	<a href="view/admin.jsp">back to ADMIN page</a>
	<br><br><br>
	<!-- <br> Lista smerova : ${sviSmerovi.size() } -->
	<!-- <br> Lista predmeta : ${sviPredmeti.size() } -->
	

	<form action="PoveziSmerPredmetController" method="get">
		<b>Predmet:</b> <select name="idPredmet">
			<c:forEach var="p" items="${sviPredmeti }">
				<option value="${p.idPredmet }">${p.nazivPredmeta }</option>
			</c:forEach>
		</select> 
		<b>Smer:</b> <select name="idSmer">
			<c:forEach var="s" items="${sviSmerovi }">
				<option value="${s.idSmer }">${s.nazivSmera }</option>
			</c:forEach>
		</select> <input type="submit" value="POVEZI" />
	</form>

	<br>

	<!-- Domaci: ispisati Smerove i tabelarno Predmete koji su vezani za te smerove -->
	<!-- Domaci: implementirati razbijanje veze izmedju odredjenog predmeta i smera -->
	
	<c:forEach var="s" items="${sviSmerovi }">
		<h4>Smer: ${s.idSmer } ---- ${s.nazivSmera}</h4>
		<table border="1px">
			<tr>
				<th>ID PREDMETA</th>
				<th>NAZIV PREDMETA</th>
				<th>SIFRA PREDMETA</th>
				<th>PREDMET-SMER</th>
			</tr>
			<c:forEach var="p" items="${s.predmetiNaSmeru }">
				<tr>
					<td>${p.idPredmet }</td>
					<td>${p.nazivPredmeta }</td>
					<td>${p.sifraPredmeta }</td>
					<td>
						<form action="UkloniPredmetSaSmeraController" method="get">
							<input type="hidden" name="idSmer" value="${s.idSmer }"> 
							<input type="hidden" name="idPredmet" value="${p.idPredmet }"> 
							<input type="submit" value="UKLONI PREDMET SA SMERA">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>


</body>
</html>