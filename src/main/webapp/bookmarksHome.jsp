<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
</head>
<body>


    <c:import url="navbar.jsp"/>
    <c:import url="sidebar.jsp"/>
    <button class="btn btn-primary">
        <a href="newBookmark" class="link-light">Create New Bookmark</a>
    </button>


    <section class="pt-5 pb-5">
        <div class="container">
            <div class="row mb-md-2">
                <c:forEach items="${bookmarks}" var="bookmark">
                    <div class="col-md-6 col-lg-3">
                        <div class="card shadow-sm border-light mb-4">
                            <div class="card-body" style="height: 200px;max-width: 100%;">
                                <h4 class="card-title">
                                    <a href="bookmarkEditor?id=${bookmark.id}">${bookmark.title}</a>
                                </h4>
                                <aside>Last Edited: ${bookmark.updated}</aside>
                                <a href="${bookmark.url}">${bookmark.url}</a>
                                <hr/>
                                <div class="post-meta overflow-auto" style="height: 80%;">
                                        ${bookmark.description}
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

</body>
</html>
