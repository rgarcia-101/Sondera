<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="head.jsp"/>
<html>
<head>

</head>
<body>
<c:import url="navbar.jsp"/>
<c:import url="sidebar.jsp"/>
    <h2>Account Details</h2>

    <h3>${user.username}</h3>
    <p>${user.firstName}, ${user.lastName}</p>
    <p>${user.email}</p>

</div>
</div>
</div>


</body>
</html>
