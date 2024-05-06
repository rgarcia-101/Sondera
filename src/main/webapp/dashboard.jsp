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
    <div class="container d-flex mt-4 p-4" style="width: 95%;margin-left: 0">
    <c:forEach items="${notes}" end="5" var="note">
        <div class="card text-truncate" style="width: 25%;max-height: 400px;">
            <div class="card-body">
                <h5 class="card-title"><a href="noteEditor?id=${note.id}">${note.title}</a></h5>
                <hr/>
                <p class="card-text">${note.content}</p>
            </div>
        </div>
    </c:forEach>
    </div>

    <br>
    <h3>Bookmarks:</h3>
    <a href="bookmarks">All bookmarks</a><br>
    <div class="container d-flex mt-4 p-4" style="width: 95%;margin-left: 0">
    <c:forEach items="${bookmarks}" end="5" var="bookmark">
        <div class="card text-truncate" style="width: 25%;max-height: 400px;">
            <div class="card-body">
                <h5 class="card-title"><a href="bookmarkEditor?id=${bookmark.id}">${bookmark.title}</a></h5>
                <a href="${bookmark.url}" class="card-link">${bookmark.url}</a>
                <p class="card-text">${bookmark.description}</p>
            </div>
        </div>
    </c:forEach>
    </div>
    <br>
    <h3>Todos:</h3>
    <a href="todos">All todo</a><br>
    <c:forEach items="${todos}" end="5" var="todo">
        <a href="todoEditor?id=${todo.id}">${todo.title}</a>
    </c:forEach>

    <br>
    <h3>Dates:</h3>
    <a href="dates">All dates</a><br>
    <c:forEach items="${dates}" end="5" var="date">
        <a href="dateEditor?id=${date.id}">${date.title}</a>
    </c:forEach>
</div>
</div>
</div>


</body>
</html>
