<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<div>
    <h2>Update user</h2>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/users/edit/submit">
        <input type="hidden" name="id" value="${userId}">
        Name: <input type="text" name="name" value="${user.name}"/><br/>

        Age: <input type="number" name="age" value="${user.age}"/><br/>

        Gender: <input type="text" name="gender" value="${user.gender}"/><br/>

        <button type="submit">Add</button>
    </form>
</div>
</body>
</html>
