<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.UserDetails" %>
<%@ page import = "model.User" %>
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
	<jsp:useBean id="user" scope="session" class="model.User"></jsp:useBean>
	<jsp:useBean id="details" scope="session" class="model.UserDetails"></jsp:useBean>
	<h1>PROFILE</h1>
	<a href="view/profesor.jsp">back to PROFESOR page</a><br><br>
	
	<h4>Username : ${user.userName }</h4>
	
	<form action="EditProfileController" method="get">
		First name: <input type="text" name="firstName" value="${details.firstName}"> <br><br>	
		Last name: <input type="text" name="lastName" value="${details.lastName}"> <br><br>		
		Country: <input type="text" name="country" value="${details.address.country}"> <br><br>	
		City: <input type="text" name="city" value="${details.address.city}"> <br><br>			
		Street: <input type="text" name="street" value="${details.address.street}"> <br><br>	
		Phone: <input type="text" name="mobilePhone" value="${details.contact.mobilePhone}"> <br><br>		
		Email: <input type="text" name="email" value="${details.contact.email}"> <br><br>		
		<input type="hidden" name="idUserDetails" value="${details.idUserDetails}">
		<input type="hidden" name="idUser" value="${user.idUser}">
		<input type="submit" value="AZURIRAJ">
	</form>
	
	
<!-- 
	<table border = "1px">
		<tr>
			<th> IME </th>
			<th> PREZIME </th>
		</tr>
		<tr>
			<td>${details.firstName }</td>
			<td>${details.lastName }</td>
		</tr>
	</table> <br>
	
	<h3>ADRESA</h3>
	<table border = "1px">
		<tr>
			<th> DRZAVA </th>
			<th> GRAD </th>
			<th> ULICA </th>
		</tr>
		<tr>
			<td>${details.address.country }</td>
			<td>${details.address.city }</td>
			<td>${details.address.street }</td>
		</tr>
	</table><br>
	
	<h3>KONTAKT</h3>
	<table border = "1px">
		<tr>
			<th> EMAIL </th>
			<th> TELEFON </th>
		</tr>
		<tr>
			<td>${details.contact.email }</td>
			<td>${details.contact.mobilePhone }</td>
		</tr>
	</table><br><br>
 -->	
	
</body>
</html>