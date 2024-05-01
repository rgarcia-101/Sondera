<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>
<body>
<%@include file="navbar.jsp"%>

<%@include file="sidebar.jsp"%>

<div class="col-sm p-3 min-vh-100">
    <h2>Dashboard</h2>

    <h3>Welcome ${user.username}</h3>
    <br>

    <br>
    <h3>Notes:</h3>
    <a href="notes">All notes</a><br>
    <c:forEach items="${notes}" end="5" var="note">
        <a href="noteEditor?id=${note.id}">${note.title}</a>
    </c:forEach>
</div>
</div>
</div>


</body>
</html>
