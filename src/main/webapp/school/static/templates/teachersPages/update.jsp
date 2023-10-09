
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Teacher Update</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/teacherupdate.css">
</head>
<body>
	<c:set var="usernameMap" value="${requestScope.userMap}" />
	<c:set var="specialitiesMap" value="${requestScope.specialitiesMap}" />

	<div>
		<form method="POST" action="${pageContext.request.contextPath}/school-app/update-teacher">
			<label for="tid">Κωδικός</label>
			<input id="tid" type="text" name="id" value="${param.id}" readonly><br>
			<label for="firstname">Όνομα</label>
			<input id="firstname" type="text" name="firstname" value="${param.firstname}"><br>
			<label for="lastname">Επώνυμο</label>
			<input id="lastname" type="text" name="lastname" value="${param.lastname}"><br>
			<label for="ssn">SSN</label>
			<input id="ssn" type="text" name="ssn" value="${param.ssn}"><br>

			<select name="username">
				<c:forEach items="${usernameMap}" var="usernameEntry">
					<c:choose>
						<c:when test="${usernameEntry.key eq param.username}">
							<option value="${usernameEntry.key}" selected>${usernameEntry.value}</option>
						</c:when>
						<c:otherwise>
							<option value="${usernameEntry.key}">${usernameEntry.value}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>

			<select name="speciality">
				<c:forEach items="${specialitiesMap}" var="specialityEntry">
					<c:choose>
						<c:when test="${specialityEntry.key eq param.speciality}">
							<option value="${specialityEntry.key}" selected>${specialityEntry.value}</option>
						</c:when>
						<c:otherwise>
							<option value="${specialityEntry.key}">${specialityEntry.value}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<br><br>
			<button type="submit">Update Teacher</button>
		</form>
	</div>
</body>
</html>

