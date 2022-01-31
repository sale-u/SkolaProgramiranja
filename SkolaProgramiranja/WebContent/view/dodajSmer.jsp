<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>dodaj Smer</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "../css/style.css"
	/>
</head>

<body>
	<h1>DODAJ SMER</h1>
	<a href="admin.jsp">back to ADMIN page</a> <br><br>
	
	<form action="../DodajSmerController" method="post">
		NAZIV SMERA: <input type="text" name="nazivSmera" required> <br>
		SIFRA SMERA: <input type="text" name="sifraSmera" required> <br><br>
		<input type="submit" value="ADD SMER">
	</form>
</body>
</html>