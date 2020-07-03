<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
<div>
    <h2>Add user</h2>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/add">
        Login: <input type="text" name="username"/><br/><br/>

        Password: <input type="text" name="password"/><br/><br/>

        Age: <input type="number" name="age"/><br/><br/>

        Role:
        <label>
            <select name="role">
                <c:forEach var="roleName" items="${userService.allRoles}">
                    <option>${roleName.name}</option>
                </c:forEach>
            </select>
        </label><br/><br/>

        <button type="submit">Add</button>
    </form>
</div>

<div>
    <div>
        <h2>List of users</h2>
    </div>
    <div>
        <table border="2">
            <tr>
                <td>Id</td>
                <td>Login</td>
                <td>Password</td>
                <td>Age</td>
                <td>Roles</td>
                <td>Actions</td>
            </tr>
            <c:forEach var="user" items="${userService.allUsers}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.age}</td>
                    <td>
                        <c:forEach var="roles" items="${userService.getRolesById(user.id)}">
                            ${roles.name}
                        </c:forEach>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/admin/delete?id=${user.id}" >Delete</a><br/>
                        <a href="${pageContext.request.contextPath}/admin/edit?id=${user.id}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
