<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students Found</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/searchresults.css">
</head>
<body>
<c:set var="usernameMap" value="${requestScope.userMap}" />
<c:set var="citiesMap" value="${requestScope.citiesMap}" />
<c:set var="birthdates" value="${requestScope.birthdates}" />

    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Gender</th>
                <th>Birthdate</th>
                <th>Username</th>
                <th>City</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            <c:forEach var = "student" items = "${requestScope.students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.firstname}</td>
                    <td>${student.lastname}</td>
                    <td>${student.gender}</td>
                    <td>${birthdates[student.id]}</td>
                    <td>${usernameMap[student.userId]}</td>
                    <td>${citiesMap[student.cityId]}</td>
                    <td><a href="${pageContext.request.contextPath}/school-app/delete-student?id=${student.id}&firstname=${student.firstname}&lastname=${student.lastname}
                &gender=${student.gender}&birthdate=${birthdates[student.id]}&username=${usernameMap[student.userId]}&city=${citiesMap[student.cityId]}"
                           onclick="return confirm('Are you sure you want to delete this student?')">Delete</a></td>

                    <td><a href="${pageContext.request.contextPath}/school-app/update-student?id=${student.id}&firstname=${student.firstname}&lastname=${student.lastname}
                  &gender=${student.gender}&birthdate=${birthdates[student.id]}&username=${student.userId}&city=${student.cityId}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>