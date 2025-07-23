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
    <p>Timezone: ${user.zone}</p>

    <form action="zonechange" method="post">
        <select class="form-select form-select-small" id="zones" name="zones" required style="max-width: 50%">
            <option selected>Update Timezone:</option>
            <option value='US/Central'>US Central</option>
            <option value='US/Pacific'>US Pacific</option>
            <option value='US/Mountain'>US Mountain</option>
            <option value='US/Eastern'>US Eastern</option>
            <option value="US/Alaska">US Alaska</option>
            <option value="US/Hawaii">US Hawaii</option>
        </select>
        </br>
        <input type="Submit" value="Submit" class="btn btn-primary">
    </form>

</div>
</div>
</div>


</body>
</html>
