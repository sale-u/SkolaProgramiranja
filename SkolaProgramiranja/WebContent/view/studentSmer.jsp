<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Student"%>
<%@ page import="model.Smer"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>veza studenata i smerova</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

<body>
	<jsp:useBean id="sviStudenti" scope="request"
		class="java.util.ArrayList"></jsp:useBean>
	<jsp:useBean id="sviSmerovi" scope="request"
		class="java.util.ArrayList"></jsp:useBean>
	<h1>VEZA STUDENATA I SMEROVA</h1>
	<a href="view/admin.jsp">back to ADMIN page</a>
	<br>
	<br>

	<form action="VezaStudentSmerController" method="post">
		<b>Smer:</b> <select name="idSmer">
			<c:forEach var="s" items="${sviSmerovi }">
				<option value="${s.idSmer }">${s.nazivSmera }</option>
			</c:forEach>
		</select> <b>Student:</b> <select name="idStudent">
			<c:forEach var="st" items="${sviStudenti }">
				<option value="${st.idUserDetails }"> ${st.indexNo } /
					${st.getFirstName()}, ${st.getLastName()}</option>
			</c:forEach>
		</select> 
		<input type="submit" value="POVEZI" name="submit"/>
		<input type="submit" value="RAZVEZI" name="submit"/>
	</form>
	<br>
	<br>

	<c:forEach var="s" items="${sviSmerovi }">
		<h4>Smer: ${s.idSmer } ---- ${s.nazivSmera}</h4>
		<table border="1">
			<tr>
				<th>ID STUDENTA</th>
				<th>BROJ INDEKSA</th>
				<th>IME STUDENTA</th>
				<th>PREZIME STUDENTA</th>
			</tr>
			<c:forEach var="st" items="${sviStudenti }">
				<c:if test="${st.smer.idSmer == s.idSmer }">
					<tr>
						<td>${st.idUserDetails }</td>
						<td>${st.indexNo }</td>
						<td>${st.firstName }</td>
						<td>${st.lastName }</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:forEach>
</body>
</html>