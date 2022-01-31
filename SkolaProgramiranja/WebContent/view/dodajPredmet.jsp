<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>dodaj Predmet</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "../css/style.css"
	/>
</head>
<body>
	
	<h1>DODAJ PREDMET</h1>
	<a href="admin.jsp">back to ADMIN page</a> <br><br>
	
	<form action="../DodajPredmetController" method="post">
		NAZIV PREDMETA: <input type="text" name="nazivPredmeta" required> <br>
		SIFRA PREDMETA: <input type="text" name="sifraPredmeta" required> <br><br>
		<input type="submit" value="ADD PREDMET">
	</form>
	
</body>
</html>