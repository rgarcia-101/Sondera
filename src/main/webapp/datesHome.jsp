<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>
</head>
<body>
<c:import url="navbar.jsp"/>
<c:import url="sidebar.jsp"/>
<button class="btn btn-primary">
    <a href="newDate" class="link-light">Create New Date</a>
</button>
<div class="d-flexbox justify-content-center align-items-center">
    <p>${calendarYear}</p>
</div>
<div class="container">
    <div class="row mb-md-2">
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">January</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[0]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">February</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[1]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">March</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[2]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">April</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[3]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">May</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[4]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">June</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[5]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">July</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[6]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">August</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[7]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">September</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[8]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">October</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[9]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">November</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[10]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="card shadow border border-light mb-4" style="height: 25em;">
                <div class="card-body">
                    <h4 class="card-title">December</h4>
                    <hr />
                    <div class="overflow-auto" style="height: 90%; max-height: 19em;">
                        <c:forEach items = "${dates[11]}" var="date">
                            <a href="dateEditor?id=${date.id}">
                                <h5 class="font-weight-normal">${date.title}</h5>
                            </a>
                            <p>${date.date}</p>
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
