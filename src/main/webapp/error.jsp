<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
    <title>Error!</title>
</head>
<body>
<c:import url="navbar.jsp"/>


<div class="text-center w-100">
    <p class="h2">Oops!</p>
    <c:choose>
        <c:when test = "${errReason == 'null'}">
            <p class="h3">The requested resource couldn`t be found</p>
        </c:when>
        <c:when test = "${errReason == 'noUser'}">
            <p class="h3">You must be logged in to do that</p>
        </c:when>
        <c:when test = "${errReason == 'wrongUser'}">
            <p class="h3">You do not have permission to view that</p>
        </c:when>
        <c:otherwise>
        <p class="h3">Something went wrong!</p>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty user}">
            <p class="h5">Return <a href="home">here!</a></p>
        </c:when>

        <c:otherwise>
            <p class="h5">Return to your dashboard <a href="home">here!</a></p>
            <p class="h5">Or, return to the main page <a href="index">here!</a></p>
        </c:otherwise>
    </c:choose>
</div>



</body>
</html>
