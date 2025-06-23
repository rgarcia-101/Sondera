<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
    <script src="todo.js"></script>
</head>
<body>

<c:import url="navbar.jsp"/>
<c:import url="sidebar.jsp"/>

    <!-- content -->
    <input class="form-control" placeholder="Title" id="noteTitle" maxlength="50" type="text" value="${object.title}" style="width: 100%; font-size: 160%;">
    <div class="d-flex p-2">
        <button type="button" class="btn btn-primary" style="margin-top: 1em;" onclick="todo();">Save</button>

        <div class="dropdown ms-auto" style="margin-top: 1em">
            <button class="btn btn-danger dropdown-toggle" type="button"
                    id="deleteBtn" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Delete
            </button>
            <form class="dropdown-menu p-4" action="delete" method="post" aria-labelledby="deleteBtn">
                <button type="submit" class="btn btn-danger">I'm Sure</button>
            </form>
        </div>

    </div>

    <p class="text-success" id="saveText"></p>
    <hr />
<%--TODO update date on save--%>
    <p id="dueDateText">Due Date: ${object.dueDate}</p>
    <p>Last Edited: ${object.updated}</p>
    <br/>
    <label for="dueDate">Set New Date:</label>
    <input type="date" id="dueDate" value="${object.dueDate}">
    <br/>
    <div class="form-outline w-100" style="height: 35%; display: flex;">
<%--        TODO textarea is too big for current limit; make smaller or make limit bigger--%>
        <textarea class="form-control bg-light" placeholder="Description" style="resize: none;" maxlength="5000" id="textInput">${object.content}</textarea>
    </div>
</div>
</div>
</div>

</body>
</html>
