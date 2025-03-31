<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
</head>
<body>
<main>
    <c:import url="navbar.jsp"/>
    <c:import url="sidebar.jsp"/>
    <button class="btn btn-primary">
        <a href="newNote" class="link-light">Create New Note</a>
    </button>

    <section class="pt-5 pb-5">
        <div class="container">
            <div class="row mb-md-2">
                <c:forEach items="${notes}" var="note">
                <div class="col-md-3 col-lg-3">
                    <div class="card shadow-sm border-light mb-4">
                        <div class="card-body" style="height: 400px; max-height: 400px;max-width: 100%;">
                            <h4 class="card-title" style="max-height: 70px;height: 70px;overflow: hidden; font-size: 1.2em;">
                                <a href="noteEditor?id=${note.id}">${note.title}</a>
                            </h4>
                            <aside>Last Edited: ${note.updated}</aside>
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
    </section>

    </div>
    </div>
</div>

</main>



</body>
</html>
