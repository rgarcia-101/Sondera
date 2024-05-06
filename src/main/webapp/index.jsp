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
        <a href="home">Go To Dashboard</a>
    </c:otherwise>
</c:choose>

<div class="d-flex justify-content-center">
    <h4>Home</h4>
</div>


</body>
</html>
