<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/updated.css">
</head>
<body>
	<div class="container">
		<h1>Νέα Στοιχεία Teacher</h1>
		<p> ${requestScope.teacher.firstname}</p>
		<p> ${requestScope.teacher.lastname}</p>
		<p> ${requestScope.teacher.ssn}</p>
		<p> ${requestScope.username}</p>
		<p> ${requestScope.speciality}</p>
	</div>
	<div class="bottom-link">
		<a href="${pageContext.request.contextPath}/school-app/teachers-menu">Επιστροφή</a>
	</div>
</body>
</html>
