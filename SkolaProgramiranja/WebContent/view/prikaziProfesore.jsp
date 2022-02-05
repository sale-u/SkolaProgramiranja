<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="wrapper.ProfesorWrapper"%>
<%@ page import="model.Predmet"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>prikazi profesore</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<h1>TABELA PROFESORA</h1>

	<jsp:useBean id="listaProfesora" scope="request" class="java.util.ArrayList"></jsp:useBean>
	
	<a href="view/admin.jsp">back to ADMIN page</a>
	<br> <br>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>USER NAME</th>
			<th>PASSWORD</th>
			<th>FIRST NAME</th>
			<th>LAST NAME</th>
			<th>EMAIL</th>
			<th>MOBILE PHONE</th>
			<th>COUNTRY</th>
			<th>CITY</th>
			<th>STREET</th>
			<th>IDENTIFICATION NO</th>
			<th>PREDMETI</th>
		</tr>

		<c:forEach var="l" items="${listaProfesora}">
			<tr>
				<td>${l.idProfesor}</td>
				<td>${l.userName}</td>
				<td>${l.password}</td>
				<td>${l.firstName}</td>
				<td>${l.lastName}</td>
				<td>${l.email}</td>
				<td>${l.mobilePhone}</td>
				<td>${l.country}</td>
				<td>${l.city}</td>
				<td>${l.street}</td>
				<td>${l.identificationNo}</td>
				<td>
					<c:url var = "povezi" value="PredmetiProfesoriController">
						<c:param name="idProfesor" value="${l.idProfesor }"></c:param>
					</c:url>
					<a href="${povezi }"><button>PREDMETI PROFESORA</button></a>
				</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>