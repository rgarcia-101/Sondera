<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<body>
<c:import url="navbar.jsp"/>


<div class="text-center w-100">
    <h2 style="font-size: 3em">Welcome to Sondera!</h2>
    <h4 style="font-size: 2em">Your center for note-taking and organization</h4>
    <c:choose>
        <c:when test="${empty user}">
            <p>Log in here:</p>
            <button class="btn btn-primary"><a href="logIn" class="text-white">Log in</a></button>
        </c:when>
        <c:otherwise>
            <p>Open your dashboard:</p>
            <a href="home">Go To Dashboard</a>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>
