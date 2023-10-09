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
        <h1>Student inserted successfully</h1>

        <p>${requestScope.insertedStudent.lastname}</p>
        <p>${requestScope.insertedStudent.firstname}</p>
        <p>${requestScope.insertedStudent.gender}</p>
        <p>${requestScope.birthdate}</p>
        <p>${requestScope.username}</p>
        <p>${requestScope.city}</p>
        <p></p>
    </div>

    <div class="bottom-link">
        <a href="${pageContext.request.contextPath}/school-app/students-menu">Επιστροφή</a>
    </div>
</body>
</html>
