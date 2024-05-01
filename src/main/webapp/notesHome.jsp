<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <script src="note.js"></script>--%>
</head>
<body>

<main>
    <%@include file="navbar.jsp"%>
    <%@include file="sidebar.jsp"%>
    <a href="newNote">Create New Note</a>
    <c:forEach items="${notes}" var="note">
        <a href="noteEditor?id=${note.id}">${note.title}</a>
    </c:forEach>
    </div>
    </div>



</main>

</body>
</html>
