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
<c:choose>
    <c:when test="${empty notes}">
        <p>No Content Found!</p>
    </c:when>
    <c:otherwise>
        <div class="container" style="margin-top: 15px;">
            <div class="row mb-md-6">
                <c:forEach items="${notes}" end="7" var="note">
                    <div class="col-md-3">
                        <div class="card shadow-sm border-light mb-4">
                            <div class="card-body" style="height: 350px; max-height: 350px;max-width: 100%;">
                                <h4 class="card-title" style="height: 65px; max-height: 65px;overflow: hidden;font-size:1.2em">
                                    <a href="noteEditor?id=${note.id}">${note.title}</a>
                                </h4>
                                <aside style="overflow:auto;font-size:.75em;">Edited: ${note.updated}</aside>
                                <hr/>
                                <div class="post-meta" style="height: 60%;overflow: auto;">
                                        ${note.content}
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:otherwise>
</c:choose>
    <br>
    <h3>Bookmarks:</h3>
    <button class="btn btn-primary">
        <a href="bookmarks" class="link-light">View All Bookmarks</a>
    </button>
<c:choose>
    <c:when test="${empty bookmarks}">
        <p>No Content Found!</p>
    </c:when>
    <c:otherwise>
        <div class="container" style="margin-top: 15px;">
            <div class="row mb-md-6">
                <c:forEach items="${bookmarks}" end="7" var="bookmark">
                    <div class="col-md-2">
                        <div class="card shadow-sm border-light mb-4">
                            <div class="card-body" style="height: 160px; max-height: 160px;max-width: 100%;">
                                <h4 class="card-title" style="height: 70px; max-height: 70px;overflow: hidden;font-size:1.2em;">
                                    <a href="${bookmark.url}">${bookmark.title}</a>
                                </h4>
                                <hr/>
                                <a href="bookmarkEditor?id=${bookmark.id}" style="overflow:hidden; max-height:30px;">edit</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:otherwise>
</c:choose>
    <br>
    <h3>Todos:</h3>
    <button class="btn btn-primary">
        <a href="todos" class="link-light">View All Todos</a>
    </button>
<c:choose>
    <c:when test="${empty todos}">
        <p>No Content Found!</p>
    </c:when>
    <c:otherwise>
        <div class="container" style="margin-top: 15px;">
            <div class="row mb-md-6">
                <c:forEach items="${todos}" end="7" var="todo">
                    <div class="col-md-3">
                        <div class="card shadow-sm border-light mb-4">
                            <div class="card-body" style="height: 350px; max-height: 350px;max-width: 100%;">
                                <h4 class="card-title" style="height: 70px; max-height: 70px;overflow: hidden;font-size:1.2em">
                                    <a href="todoEditor?id=${todo.id}">${todo.title}</a>
                                </h4>
                                <aside style="overflow:auto;font-size:.75em;">Due: ${todo.dueDate}</aside>
                                <hr/>
                                <div class="post-meta" style="height: 60%;overflow: auto;">
                                        ${todo.content}
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</div>
</div>
</div>


</body>
</html>
