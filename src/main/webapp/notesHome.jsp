<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
<%--    <script src="note.js"></script>--%>
</head>
<body>

<main>
    <c:import url="navbar.jsp"/>
    <c:import url="sidebar.jsp"/>
    <button class="btn btn-primary">
        <a href="newNote" class="link-light">Create New Note</a>
    </button>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--    <c:forEach items="${notes}" var="note">--%>
<%--        <div note-id="${note.id}" class="col-sm-3 bg-light" style="height: 10%;width: 15%;">--%>
<%--            <a href="bookmarkEditor?id=${note.id}">${note.title}</a>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--        </div>--%>
<%--    </div>--%>

    <section class="pt-5 pb-5">
        <div class="container">
            <div class="row mb-md-2">
                <c:forEach items="${notes}" var="note">
                <div class="col-md-6 col-lg-3">
                    <div class="card shadow-sm border-light mb-4">
                        <div class="card-body" style="height: 400px;max-width: 100%;">
                            <h4 class="card-title">
                                <a href="noteEditor?id=${note.id}">${note.title}</a>
                            </h4>
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
    </section>

    </div>
    </div>



</main>

</body>
</html>
