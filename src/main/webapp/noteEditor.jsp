<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="note.js"></script>
</head>
<body>

<main>
    <%@include file="navbar.jsp"%>


    <%@include file="sidebar.jsp"%>
    <div class="col-sm p-3 min-vh-100">
        <!-- content -->
        <input class="form-control" placeholder="Title" id="noteTitle" type="text" value="${object.title}" style="width: 100%; font-size: 160%;">
        <div class="d-flex p-2">
            <button type="button" class="btn btn-primary" style="margin-top: 1em;" onclick="note();">Save</button>

            <div class="dropdown ms-auto" style="margin-top: 1em">
                <button class="btn btn-danger dropdown-toggle" type="button"
                        id="deleteBtn" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Delete
                </button>
                <form class="dropdown-menu p-4" action="delete" method="post" aria-labelledby="deleteBtn">
                    <button type="submit" class="btn btn-danger">I'm sure</button>
                </form>
            </div>

        </div>

        <p class="text-success" id="saveText"></p>
        <hr />
        <div class="form-outline w-100" style="height: 80%; display: flex;">
            <textarea class="form-control bg-light" placeholder="Content" style="resize: none" id="textInput">${object.content}</textarea>
        </div>
    </div>
    </div>
    </div>




<%--    <button type="button" class="btn btn-primary" style="margin-top: 1em;" onclick="note();">Save</button>--%>

<%--    <label for="textInput"></label>--%>

</main>

</body>
</html>
