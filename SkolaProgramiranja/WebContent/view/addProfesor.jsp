<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>add Profesor</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "../css/style.css"
	/>
</head>
<body>
	<h1>DODAJ PROFESORA</h1>
	<a href="admin.jsp">back to ADMIN page</a> <br><br>
	
	<form action="../AddProfesorController" method="get">
		USER NAME : <input type="text" name="userName" required> <br>
		PASSWORD : <input type="text" name="password" required> <br><br>
		
		FIRST NAME : <input type="text" name="firstName"> <br>
		LAST NAME : <input type="text" name="lastName"> <br>
		EMAIL: <input type="email" name="email"> <br>
		PHONE : <input type="text" name="mobilePhone"> <br>
		COUNTRY : <input type="text" name="country"> <br>
		CITY : <input type="text" name="city"> <br>
		STREET : <input type="text" name="street"> <br>
		IDENTIFICATION NO : <input type="text" name="identificationNo"> <br><br>
		<input type="submit" value="ADD PROFESOR">
	</form>
	
</body>
</html>