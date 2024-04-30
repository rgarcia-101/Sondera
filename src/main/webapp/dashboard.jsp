<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>
<body>
<h2>Dashboard</h2>

<h3>Welcome ${user.username}</h3>
<br>
<a href="logout">Log out</a>

<br>
<h3>Notes:</h3>
<a href="notes">All notes</a><br>
<c:forEach items="${notes}" end="5" var="note">
    <a href="notes?id=${note.id}">${note.title}</a>
</c:forEach>

</body>
</html>
