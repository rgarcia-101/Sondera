<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>
<body>
<h2>org</h2>


<c:choose>
    <c:when test="${empty user}">
        <a href = "logIn">Log in</a>
    </c:when>
    <c:otherwise>
        <h3>Welcome ${user.username}</h3>
        <br>
        <a href="logout">Log out</a>
    </c:otherwise>
</c:choose>


</body>
</html>
