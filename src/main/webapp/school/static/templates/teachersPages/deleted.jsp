<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/deleted.css">
</head>
<body>
	<div class="container">
		<p>Teacher: ${param.id} ${param.firstname} ${param.lastname} ${requestScope.ssn}
			${param.username} ${param.speciality} was deleted</p>
	</div>

	<div class="bottom-link">
		<a href="${pageContext.request.contextPath}/school-app/teachers-menu">Επιστροφή</a>
	</div>
</body>
</html>
