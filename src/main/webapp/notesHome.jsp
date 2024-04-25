<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="note.js"></script>
</head>
<body>

<main>
    <c:forEach items="${noteList}" var="note">
        <a href="notes?id=${note.id}">${note.title}</a>
    </c:forEach>


</main>

</body>
</html>
