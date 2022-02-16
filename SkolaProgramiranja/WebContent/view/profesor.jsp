<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "model.User" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Profesor</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "../css/style.css"
	/>
	<!--  Linkovi za BootStrap5 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<jsp:useBean id="user" scope="session" class="model.User"></jsp:useBean>
	<h1>Dobrodosli na Profesorsku stranicu</h1>
	
	<p style = "display: inline;">Ulogovan je profesor : <b>${user.userName}</b> </p>
	<a href="logout.jsp" style = "margin-left: 70px;">Logout <img src="../slike/logout.jfif"/></a> <br><br>
	<img src = "../slike/Profesor.png"/> <br> <br>
	
	<form action = "../ProfileController" method = "get">
		<input type="hidden" value="${user.idUser }" name = "idUser">
		<input type="submit" value="VIEW-EDIT PROFILE">
	</form>
	
	<form action = "../ProfesorController" method = "get">
		<input type="hidden" value="${user.idUser }" name = "idUser"> <br>
		<input type="submit" value="MOJI PREDMETI">
	</form>


</body>

</html>