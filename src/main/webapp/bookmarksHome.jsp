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
                            <div class="card-body" style="height: 250px;max-height: 250px;max-width: 100%;">
                                <h4 class="card-title" style="max-height: 50px;height: 50px;overflow: hidden; font-size: 1.2em;">
                                    <a href="${bookmark.url}">${bookmark.title}</a>
                                </h4>
                                <aside style="height:25px;max-height:25px;overflow:hidden;">Last Edited: ${bookmark.updated}</aside>
                                <a href="bookmarkEditor?id=${bookmark.id}" style="height:25px;max-height:25px;overflow:hidden;">Edit</a>
                                <hr/>
                                <div class="post-meta overflow-auto" style="max-height: 40%;overflow: hidden;">
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
