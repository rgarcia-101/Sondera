<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="z-3 navbar navbar-expand-lg navbar-dark navbar-fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">Sondera</a>
        <c:choose>
            <c:when test="${empty user}">
                <a href = "${pageContext.request.contextPath}/logIn" class="ms-auto link-light">Log in</a>
            </c:when>
            <c:otherwise>
                <%--    TODO make page for user profile--%>
                <%--    TODO add a profile link to dropdown--%>
                <div class="dropdown ms-auto">
                    <button class="btn dropdown-toggle" type="button"
                            id="accMenu" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="images/usericon.png" style="width: 32px" alt="user">
                    </button>
                    <div class="dropdown-menu" aria-labelledby="accMenu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/account">Account</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>