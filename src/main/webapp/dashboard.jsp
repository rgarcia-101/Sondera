<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>

</head>
<body>
<c:import url="navbar.jsp"/>
<c:import url="sidebar.jsp"/>
    <h2>Dashboard</h2>

    <h3>Welcome, ${user.username}</h3>
    <br><br>
    <h3>Notes:</h3>
    <button class="btn btn-primary">
        <a href="notes" class="link-light">View All Notes</a>
    </button>
    <div class="container" style="margin-top: 15px;">
        <div class="row mb-md-6">
            <c:forEach items="${notes}" end="4" var="note">
                <div class="col-md-2">
                    <div class="card shadow-sm border-light mb-4">
                        <div class="card-body" style="height: 300px;max-width: 100%;">
                            <h4 class="card-title">
                                <a href="noteEditor?id=${note.id}">${note.title}</a>
                            </h4>
                            <aside>Last Edited: ${note.updated}</aside>
                            <hr/>
                            <div class="post-meta overflow-auto" style="height: 80%;">
                                    ${note.content}
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br>
    <h3>Bookmarks:</h3>
    <button class="btn btn-primary">
        <a href="bookmarks" class="link-light">View All Bookmarks</a>
    </button>
    <div class="container" style="margin-top: 15px;">
        <div class="row mb-md-6">
            <c:forEach items="${bookmarks}" end="4" var="bookmark">
                <div class="col-md-2">
                    <div class="card shadow-sm border-light mb-4">
                        <div class="card-body" style="height: 100px;max-width: 100%;">
                            <h4 class="card-title">
                                <a href="bookmarkEditor?id=${bookmark.id}">${bookmark.title}</a>
                            </h4>
                            <a href="${bookmark.url}">${bookmark.url}</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br>
    <h3>Todos:</h3>
    <button class="btn btn-primary">
        <a href="todos" class="link-light">View All Todos</a>
    </button>
    <div class="container" style="margin-top: 15px;">
        <div class="row mb-md-6">
            <c:forEach items="${todos}" end="4" var="todo">
                <div class="col-md-2">
                    <div class="card shadow-sm border-light mb-4">
                        <div class="card-body" style="height: 300px;max-width: 100%;">
                            <h4 class="card-title">
                                <a href="todoEditor?id=${todo.id}">${todo.title}</a>
                            </h4>
                            <aside>Due: ${todo.dueDate}</aside>
                            <hr/>
                            <div class="post-meta overflow-auto" style="height: 80%;">
                                    ${todo.content}
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


<%--    <div class="container d-flex mt-4 p-4" style="width: 95%;margin-left: 0">--%>
<%--    <c:forEach items="${todos}" end="5" var="todo">--%>
<%--        <div class="card text-truncate" style="width: 25%;max-height: 400px;">--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title"><a href="todoEditor?id=${todo.id}">${todo.title}</a></h5>--%>
<%--                <h4>${todo.dueDate}</h4>--%>
<%--                <hr/>--%>
<%--                <p class="card-text">${todo.content}</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    </div>--%>
</div>
</div>
</div>


</body>
</html>
