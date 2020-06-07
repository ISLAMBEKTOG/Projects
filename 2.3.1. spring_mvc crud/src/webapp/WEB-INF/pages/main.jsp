<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <h2>Add user</h2>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/users/add">
        Name: <input type="text" name="name"/><br/>

        Age: <input type="number" name="age"/><br/>

        Gender: <input type="text" name="gender"/><br/>

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
                <td>Name</td>
                <td>Age</td>
                <td>Gender</td>
                <td>Action</td>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.age}"/></td>
                    <td><c:out value="${user.gender}"/></td>
                    <td><a href="${pageContext.request.contextPath}/users/delete?id=${user.id}" >Delete</a><br/>
                        <a href="${pageContext.request.contextPath}/users/edit?id=${user.id}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
