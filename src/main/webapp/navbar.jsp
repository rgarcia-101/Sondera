<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark navbar-fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="index">Sondera</a>
        <c:choose>
            <c:when test="${empty user}">
                <a href = "logIn" class="ms-auto link-light">Log in</a>
            </c:when>
            <c:otherwise>
                <div class="dropdown ms-auto">
                    <button class="btn dropdown-toggle" type="button"
                            id="accMenu" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="images/usericon.png" style="width: 32px" alt="user">
                    </button>
                    <div class="dropdown-menu" aria-labelledby="accMenu">
                        <a class="dropdown-item" href="logout">Logout</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>