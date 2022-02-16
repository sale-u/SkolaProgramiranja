<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.Profesor" %>
<%@ page import = "model.UserDetails" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link
		type = "text/css"
		rel = "stylesheet"
		href = "css/style.css"
	/>
</head>

<body>
	<h1>PROFESOROVI PREDMETI</h1>
	
	<jsp:useBean id="profesor" scope="request" class="model.Profesor"></jsp:useBean>
	<jsp:useBean id="detalj" scope="request" class="model.UserDetails"></jsp:useBean>
	
	<a href = "view/profesor.jsp">back to PROFESOR page</a>
	<br><br>
	
	<h4>Profesor : ${detalj.firstName } ${detalj.lastName }</h4>
	
	<br>
	<table>
		<tr>
			<th>NAZIVI PREDMETA KOJE PREDAJE:</th>
		</tr>
		<c:forEach var = "p" items = "${profesor.predmetiKojePredaje }">
			<tr>
				<td>
					<c:url var = "pp" value="SpisakStudenataController">
						<c:param name="idPredmeta" value="${p.idPredmet }"/>
					</c:url>
					<a href = "${pp }">${p.nazivPredmeta }</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>