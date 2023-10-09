<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teachers Search</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/teachersmenu.css">
</head>
<body>
  <div class="center">
    <p>Hello ${sessionScope.loginName}</p>
  </div>

  <div class="center">
      <div class="search-wrapper">
        <div class="bot-gap">
          <span class="title">Teachers Search</span>
        </div>
        <div class="bot-gap">
          <form method="POST" action="${pageContext.request.contextPath}/school-app/search-teacher">
            <input name="lastname" type="text" class="search rounded" placeholder="Insert teacher's lastname"
                   autofocus/>
            <br><br>
            <button class="search-btn rounded color-btn" type="submit">Search</button>
          </form>
        </div>
      </div>

      <div class="insert-wrapper">
        <div class="bot-gap">
          <span class="title">Teachers Insert</span>
        </div>
        <div class="bot-gap">
          <form method="POST" action="${pageContext.request.contextPath}/school-app/insert-teacher">
            <input name="lastname" type="text" value="${param.lastname}"
                   class="insert rounded" placeholder="Last name" autofocus/><br>
            <input name="firstname" type="text" value="${param.firstname}"
                   class="insert rounded" placeholder="First name" autofocus/>
            <input name="ssn" type="text" value="${param.ssn}"
                   class="insert rounded" placeholder="SSN" autofocus/>

            <c:set var="usernameMap" value="${requestScope.usernameMap}" />
            <c:set var="specialityMap" value="${requestScope.specialitiesMap}" />


            <select name="username" class="insert rounded">
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

            <select name="speciality" class="insert rounded">
              <c:forEach items="${specialityMap}" var="specialityEntry">
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

            <br> <br>

            <button class="search-btn rounded color-btn" type="submit">Insert</button>

          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>


