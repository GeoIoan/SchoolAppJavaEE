<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teachers Found</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/searchresults.css">

</head>
<body>
    <c:set var="usernameMap" value="${requestScope.userMap}" />
    <c:set var="specialityMap" value="${requestScope.specialitiesMap}" />

  <div>
    <table>
      <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>SSN</th>
        <th>Username</th>
        <th>Speciality</th>
        <th>Delete</th>
        <th>Update</th>
      </tr>
      <c:forEach var = "teacher" items = "${requestScope.teachers}">
        <tr>
          <td>${teacher.id}</td>
          <td>${teacher.firstname}</td>
          <td>${teacher.lastname}</td>
          <td>${teacher.ssn}</td>
          <td>${usernameMap[teacher.userId]}</td>
          <td>${specialityMap[teacher.specialityId]}</td>
          <td><a href="${pageContext.request.contextPath}/school-app/delete-teacher?id=${teacher.id}&firstname=${teacher.firstname}&lastname=${teacher.lastname}
              &ssn=${teacher.ssn}&username=${usernameMap[teacher.userId]}&speciality=${specialityMap[teacher.specialityId]}"
                 onclick="return confirm('Are you sure you want to delete this teacher?')">Delete</a></td>

          <td><a href="${pageContext.request.contextPath}/school-app/update-teacher?id=${teacher.id}&firstname=${teacher.firstname}&lastname=${teacher.lastname}
                        &ssn=${teacher.ssn}&username=${teacher.userId}&speciality=${teacher.specialityId}">Update</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>
