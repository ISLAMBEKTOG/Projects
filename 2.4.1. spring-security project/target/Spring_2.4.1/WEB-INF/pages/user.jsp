<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 16.06.2020
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
<div>
    <p>User id: ${userInfo.id}</p>
    <p>User login: ${userInfo.login}</p>
    <p>User password: ${userInfo.password}</p>
    <p>User age: ${userInfo.age}</p>
    <p>Roles: </p>
    <c:forEach var="role" items="${userService.getRolesById(userInfo.id)}">
        <p>${role.name}</p><br/>
    </c:forEach>
</div>
</body>
</html>
