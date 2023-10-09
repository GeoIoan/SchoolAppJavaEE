
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/school/static/css/register.css">
</head>
<body>

<div class="container-fluid">
    <div class="container">
        <div class="row">
            <h1 class="text-grey">Register</h1>
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/register">
            <div class="row">
                <input type="email" name="username" required placeholder="E-mail">
                <span></span>
            </div>
            <div class="row">
                <input type="password" name="password" required placeholder="Password">
                <span></span>
            </div>
            <div class="row">
                <input type="password" name="confirmedPassword" required placeholder="Confirm Password">
                <span></span>
            </div>
            <div class="row">
                <button type="submit">Sign Up</button>
            </div>
        </form>
    </div>
        <div class="row">
            <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in here!</a></p>
        </div>
    </div>

    <div class=container>
        <c:if test="${requestScope.isError eq 'true'}">
            <p style="color: red">${param.message}</p>
        </c:if>
    </div>

    <div class=container>
    <c:if test="${requestScope.success eq 'true'}">
        <p style="color: blue">New user was registered successfully</p>
    </c:if>
    </div>
</body>
</html>
