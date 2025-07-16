<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
    <script src="date.js"></script>
</head>
<body>

<c:import url="navbar.jsp"/>
<c:import url="sidebar.jsp"/>
<div class="col-sm p-3 min-vh-100">
    <!-- content -->
    <input class="form-control" placeholder="Title" maxlength="40" id="dateTitle" type="text" value="${object.title}" style="width: 100%; font-size: 160%;">
    <div class="d-flex p-2">
        <button type="button" class="btn btn-primary" style="margin-top: 1em;" onclick="date();">Save</button>

        <div class="dropdown ms-auto" style="margin-top: 1em">
            <button class="btn btn-danger dropdown-toggle" type="button"
                    id="deleteBtn" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Delete
            </button>
            <form class="dropdown-menu p-4" action="delete" method="post" aria-labelledby="deleteBtn">
                <input type="hidden" id="objId" name="objId" value="${object.id}">
                <input type="hidden" id="objType" name="objType" value="date">
                <button type="submit" class="btn btn-danger">I'm sure</button>
            </form>
        </div>

    </div>

    <p class="text-success" id="saveText"></p>
    <hr />
    <p id="DateText">Date: ${object.date}</p>
    <p>Last Edited: ${object.updated}</p>
    <br/>
    <label for="setDate">Set New Date:</label>
    <input type="date" id="setDate" value="${object.date}">
    <div class="form-outline w-100" style="height: 20%; display: flex;">
        <textarea class="form-control bg-light" maxlength="500" placeholder="Description" style="resize: none" id="textInput">${object.content}</textarea>
    </div>
</div>
</div>
</div>

</body>
</html>
