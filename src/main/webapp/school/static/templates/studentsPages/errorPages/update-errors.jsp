<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/errors2.css">
</head>
<body>
    <div>
        <c:if test="${requestScope.errorMessage ne ''}">
            <p>${requestScope.errorMessage}</p>
        </c:if>
        <c:if test="${requestScope.errorMessages ne ''}">
            <c:forEach var="error" items="${requestScope.errorMessages}">
                <p>${error}</p>
            </c:forEach>
        </c:if>
    </div>
<a href="${pageContext.request.contextPath}/school-app/update-student?id=${requestScope.studentToUpdate.id}&firstname=${requestScope.studentToUpdate.firstname}&lastname=${requestScope.studentToUpdate.lastname}
&gender=${requestScope.studentToUpdate.gender}&birthdate=${requestScope.birthdate}&username=${requestScope.studentToUpdate.username}&city=${requestScope.studentToUpdate.city}">Επιστροφή</a>
</body>
</html>

