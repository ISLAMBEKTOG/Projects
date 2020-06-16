<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<div>
    <h2>Update user</h2>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/edit/submit">
        <input type="hidden" name="id" value="${userId}">
        Login: <input type="text" name="login" value="${user.login}"/><br/><br/>

        Password: <input type="text" name="password" value="${user.password}"/><br/><br/>

        Age: <input type="number" name="age" value="${user.age}"/><br/><br/>

        <button type="submit">Update</button>
    </form>
</div>
</body>
</html>
