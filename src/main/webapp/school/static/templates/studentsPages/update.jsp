<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Update</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/studentsupdate.css">
</head>
<body>
<c:set var="usernameMap" value="${requestScope.userMap}" />
<c:set var="citiesMap" value="${requestScope.citiesMap}" />

    <div>
        <form method="POST" action="${pageContext.request.contextPath}/school-app/update-student">
            <label for="tid">Κωδικός</label>
            <input id="tid" type="text" name="id" value="${param.id}" readonly><br>
            <label for="firstname">Όνομα</label>
            <input id="firstname" type="text" name="firstname" value="${param.firstname}"><br>
            <label for="lastname">Επώνυμο</label>
            <input id="lastname" type="text" name="lastname" value="${param.lastname}"><br>

            <input class="insert rounded" type="radio" name="gender" id="male" value="M" <%= "M".equals(request.getParameter("gender")) ? "checked" : "" %>>
            <label for="male">Male</label>
            <input class="insert rounded" type="radio" name="gender" id="female" value="F" <%= "F".equals(request.getParameter("gender")) ? "checked" : "" %>>
            <label for="female">Female</label>

            <input class="insert rounded" type="date" name="birthdate" value="<%= request.getParameter("birthdate") %>">



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

            <select name="city">
                <c:forEach items="${citiesMap}" var="cityEntry">
                    <c:choose>
                        <c:when test="${cityEntry.key eq param.city}">
                            <option value="${cityEntry.key}" selected>${cityEntry.value}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${cityEntry.key}">${cityEntry.value}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <br><br>
            <button type="submit">Update Student</button>
        </form>
    </div>
</body>
</html>