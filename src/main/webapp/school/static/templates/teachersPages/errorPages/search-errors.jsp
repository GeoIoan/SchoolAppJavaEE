
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/errors1.css">
</head>
<body>
    <div>
        <p>${requestScope.errorMessage}</p>
    </div>
    <a href="${pageContext.request.contextPath}/school-app/teachers-menu">Επιστροφή</a>
</body>
</html>
