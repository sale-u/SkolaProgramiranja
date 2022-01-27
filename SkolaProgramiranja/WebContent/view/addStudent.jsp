<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>add Student</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "../css/style.css"
	/>
</head>
<body>
	<h1>DODAJ STUDENTA</h1>
	<a href="admin.jsp">back to ADMIN page</a> <br><br>
	
	<form action="../AddStudentController" method="get">
		USER NAME : <input type="text" name="userName"> <br>
		PASSWORD : <input type="text" name="password"> <br>
		FIRST NAME : <input type="text" name="firstName"> <br>
		LAST NAME : <input type="text" name="lastName"> <br>
		EMAIL: <input type="email" name="email"> <br>
		PHONE : <input type="text" name="mobilePhone"> <br>
		COUNTRY : <input type="text" name="country"> <br>
		CITY : <input type="text" name="city"> <br>
		STREET : <input type="text" name="street"> <br>
		INDEX NO : <input type="text" name="indexNo"> <br><br>
		<input type="submit" value="ADD STUDENT">
	</form>
	
</body>
</html>