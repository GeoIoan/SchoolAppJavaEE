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
        <p>Student: ${param.id} ${param.firstname} ${param.lastname} ${param.gender} ${param.birthdate}
        ${param.username} ${param.city} was deleted</p>
    </div>

    <div class="bottom-link">
        <a href="${pageContext.request.contextPath}/school-app/students-menu">Επιστροφή</a>
    </div>
</body>
</html>
