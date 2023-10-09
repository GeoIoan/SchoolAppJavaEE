<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>Q
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/updated.css">
</head>
<body>
    <div class="container">
        <h1>Νέα Στοιχεία Student</h1>
        <p> ${requestScope.student.firstname}</p>
        <p> ${requestScope.student.lastname}</p>
        <p> ${requestScope.student.gender}</p>
        <p> ${requestScope.birthdate}</p>
        <p> ${requestScope.username}</p>
        <p> ${requestScope.city}</p>
    </div>
    <div class="bottom-link">
        <a href="${pageContext.request.contextPath}/school-app/students-menu">Επιστροφή</a>
    </div>
</body>
</html>
