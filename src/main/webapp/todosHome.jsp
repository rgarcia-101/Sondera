<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>

<html>
<head>
</head>
<body>
<c:import url="navbar.jsp"/>
<c:import url="sidebar.jsp"/>
<button class="btn btn-primary">
<a href="newTodo" class="link-light">Create New Todo</a>
</button>
<div class="container" style="height: 50em;">
    <div class="row mb-md-2">
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 48em;">
                <div class="card-body">
                    <h4 class="card-title">Overdue</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 41em;">
                        <c:forEach items = "${todos[0]}" var="todo">
                            <a href="todoEditor?id=${todo.id}">
                                <h5 class="font-weight-normal">${todo.title}</h5>
                            </a>
                        <div class="overflow-hidden" style="max-height: 7%;">
                            <p>${todo.dueDate}
                            </p>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 48em;">
                <div class="card-body">
                    <h4 class="card-title">Today</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 41em;">
                        <c:forEach items = "${todos[1]}" var="todo">
                            <a href="todoEditor?id=${todo.id}">
                                <h5 class="font-weight-normal">${todo.title}</h5>
                            </a>
                            <div class="overflow-hidden" style="max-height: 7%;">
                                <p>${todo.dueDate}
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 48em;">
                <div class="card-body">
                    <h4 class="card-title">This Week</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 41em;">
                        <c:forEach items = "${todos[2]}" var="todo">
                            <a href="todoEditor?id=${todo.id}">
                                <h5 class="font-weight-normal">${todo.title}</h5>
                            </a>
                            <div class="overflow-hidden" style="max-height: 7%;">
                                <p>${todo.dueDate}
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 48em;">
                <div class="card-body">
                    <h4 class="card-title">Further Out</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 41em;">
                        <c:forEach items = "${todos[3]}" var="todo">
                            <a href="todoEditor?id=${todo.id}">
                                <h5 class="font-weight-normal">${todo.title}</h5>
                            </a>
                            <div class="overflow-hidden" style="max-height: 7%;">
                                <p>${todo.dueDate}
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




</div>
</div>
</div>


</body>
</html>
