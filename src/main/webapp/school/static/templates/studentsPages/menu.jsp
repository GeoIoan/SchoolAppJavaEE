<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teachers Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/studentsmenu.css">
</head>
<body>
    <div class="center">
        <p>Hello ${sessionScope.loginName}</p>
    </div>

    <div class="center">
        <div class="search-wrapper">
            <div class="bot-gap">
                <span class="title">Students Search</span>
            </div>
            <div class="bot-gap">
                <form method="POST" action="${pageContext.request.contextPath}/school-app/search-student">
                    <input name="lastname" type="text" class="search rounded" placeholder="Insert student's lastname"
                           autofocus/>
                    <br><br>
                    <button class="search-btn rounded color-btn" type="submit">Search</button>
                </form>
            </div>
        </div>

        <div class="insert-wrapper">
                <div class="bot-gap">
                    <span class="title">Students Insert</span>
                </div>
                <div class="bot-gap">
                    <form method="POST" action="${pageContext.request.contextPath}/school-app/insert-student">
                        <input name="lastname" type="text"
                               class="insert rounded" placeholder="Last name" value="${param.firstname}" autofocus/><br>
                        <input name="firstname" type="text"
                               class="insert rounded" placeholder="First name" value="${param.lastname}" autofocus/>

                        <div class="radio-group">
                            <input type="radio" name="gender" id="male" value="M" <%= "M".equals(request.getParameter("gender")) ? "checked" : "" %>>
                            <label for="male">Male</label>
                            <input type="radio" name="gender" id="female" value="F" <%= "F".equals(request.getParameter("gender")) ? "checked" : "" %>>
                            <label for="female">Female</label>
                        </div>

                        <input class="insert rounded" type="date" name="birthdate" value="<%= request.getParameter("birthdate") %>">


                        <c:set var="usernameMap" value="${requestScope.usernameMap}" />
                        <c:set var="cityMap" value="${requestScope.citiesMap}" />

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

                        <select name="city" class="insert rounded">
                            <c:forEach items="${cityMap}" var="cityEntry">
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

                        <br> <br>

                        <button class="search-btn rounded color-btn" type="submit">Insert</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>