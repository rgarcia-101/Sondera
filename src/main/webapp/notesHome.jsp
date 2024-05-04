<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<%--    <script src="note.js"></script>--%>
</head>
<body>

<main>
    <%@include file="navbar.jsp"%>
    <%@include file="sidebar.jsp"%>
    <a href="newNote">Create New Note</a>
    <div class="container">
        <div class="row">
    <c:forEach items="${notes}" var="note">
        <div note-id="${note.id}" class="col-sm-3 bg-light" style="height: 10%;width: 15%;">
            <a href="bookmarkEditor?id=${note.id}">${note.title}</a>
        </div>
    </c:forEach>
        </div>
    </div>
    </div>
    </div>



</main>

</body>
</html>
