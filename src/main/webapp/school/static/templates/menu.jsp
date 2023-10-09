<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/school/static/css/menu.css">
</head>
<body>
<div class="menu">
    <a href="${pageContext.request.contextPath}/school-app/teachers-menu">Teachers</a>
    <a href="${pageContext.request.contextPath}/school-app/students-menu">Students</a>
</div>
</body>
</html>
