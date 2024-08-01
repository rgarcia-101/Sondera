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
    <div class="container d-flex mt-4 p-4" style="width: 95%;margin-left: 0">
    <c:forEach items="${notes}" end="5" var="note">
        <div class="card text-truncate" style="width: 25%;max-height: 400px;">
            <div class="card-body">
                <h5 class="card-title"><a href="noteEditor?id=${note.id}">${note.title}</a></h5>
                <p>Last Edited:<br/> ${note.updated}</p>
                <hr/>
                <p class="card-text">${note.content}</p>
            </div>
        </div>
    </c:forEach>
    </div>

    <br>
    <h3>Bookmarks:</h3>
    <button class="btn btn-primary">
        <a href="bookmarks" class="link-light">View All Bookmarks</a>
    </button>
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
    <button class="btn btn-primary">
        <a href="todos" class="link-light">View All Todos</a>
    </button>
    <div class="container d-flex mt-4 p-4" style="width: 95%;margin-left: 0">
    <c:forEach items="${todos}" end="5" var="todo">
        <div class="card text-truncate" style="width: 25%;max-height: 400px;">
            <div class="card-body">
                <h5 class="card-title"><a href="todoEditor?id=${todo.id}">${todo.title}</a></h5>
                <h4>${todo.dueDate}</h4>
                <hr/>
                <p class="card-text">${todo.content}</p>
            </div>
        </div>
    </c:forEach>
    </div>
</div>
</div>
</div>


</body>
</html>
