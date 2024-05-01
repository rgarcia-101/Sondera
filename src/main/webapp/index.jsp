<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>
<body>
<%@include file="navbar.jsp"%>
<h2>org</h2>


<c:choose>
    <c:when test="${empty user}">
        <a href = "logIn">Log in</a>
    </c:when>
    <c:otherwise>
        <h3>Welcome ${user.username}</h3>
        <br>
        <a href="logout">Log out</a>
        <a href="home">My Dashboard</a>
    </c:otherwise>
</c:choose>


</body>
</html>
