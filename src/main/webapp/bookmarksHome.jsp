<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<main>

    <%@include file="navbar.jsp"%>
    <%@include file="sidebar.jsp"%>
    <a href="newBookmark">Create New Bookmark</a>
    <div class="container">
        <div class="row">
            <c:forEach items="${bookmarks}" var="bookmark">
                <div bookmark-id="${bookmark.id}" class="col-sm-3 bg-light" style="height: 10%;width: 15%;">
                    <a href="bookmarkEditor?id=${bookmark.id}">${bookmark.title}</a>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
    </div>


</main>

</body>
</html>
