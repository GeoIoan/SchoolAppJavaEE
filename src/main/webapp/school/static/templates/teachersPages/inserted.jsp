<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Επιτυχής Εισαγωγή</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/inserted.css">
</head>
<body>
	<div class="container">
		<h1>Teacher inserted successfully</h1>

		<p>${requestScope.insertedTeacher.lastname}</p>
		<p>${requestScope.insertedTeacher.firstname}</p>
		<p>${requestScope.insertedteacher.ssn}</p>
		<p>${requestScope.username}</p>
		<p>${requestScope.speciality}</p>
		<p></p>
	</div>

	<div class="bottom-link">
		<a href="${pageContext.request.contextPath}/school-app/teachers-menu">Επιστροφή</a>
	</div>
</body>
</html>