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
        <input class="form-control" id="noteTitle" type="text" value="${noteTitle}" style="width: 80%; font-size: 160%;">
        <button type="button" class="btn btn-primary" style="margin-top: 1em;" onclick="note();">Save</button>
        <p class="text-success" id="saveText"></p>
        <hr />
        <div class="form-outline w-100" style="height: 80%; display: flex;">
            <textarea class="form-control bg-light" style="resize: none" id="textInput">${textContent}</textarea>
        </div>
    </div>
    </div>
    </div>




<%--    <button type="button" class="btn btn-primary" style="margin-top: 1em;" onclick="note();">Save</button>--%>

<%--    <label for="textInput"></label>--%>

</main>

</body>
</html>
