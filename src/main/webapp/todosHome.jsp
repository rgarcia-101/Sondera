<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>

<html>
<head>
</head>
<body>
<%@include file="navbar.jsp"%>
<%@include file="sidebar.jsp"%>
<a href="newTodo">Create New Todo</a>
<div class="container" style="width: 90%;">

<div class="row">
    <h4>Overdue</h4>
        <c:forEach items = "${todos[0]}" var="todo">
            <div class="col-sm-4 col-lg-12">
                <a href="todoEditor?id=${todo.id}">${todo.title}</a>
            ${todo.dueDate}
            </div>
        </c:forEach>
</div>
<div class="row">
    <h4>Today</h4>
        <c:forEach items = "${todos[1]}" var="todo">
            <div class="col-sm-4 col-lg-12">
                <a href="todoEditor?id=${todo.id}">${todo.title}</a>
            ${todo.dueDate}
            </div>
        </c:forEach>
</div>
<div class="row">
    <h4>This Week</h4>
        <c:forEach items = "${todos[2]}" var="todo">
            <div class="col-sm-4 col-lg-12">
                <a href="todoEditor?id=${todo.id}">${todo.title}</a>
            ${todo.dueDate}
            </div>
        </c:forEach>
</div>
<div class="row">
    <h4>Further Out</h4>
        <c:forEach items = "${todos[3]}" var="todo">
            <div class="col-sm-4 col-lg-12">
                <a href="todoEditor?id=${todo.id}">${todo.title}</a>
                ${todo.dueDate}
            </div>
        </c:forEach>
</div>
</div>
</div>
</div>


</body>
</html>
