<%@ page import="service.Service" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 28.05.2020
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        <h2>Update user <%= request.getParameter("id") %>
        </h2>
    </div>
    <div>
        <form method="post" action="edit">
            <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
            Login: <input type="text" name="login" value="<%= request.getAttribute("login") %>"/><br/>

            Password: <input type="password" name="password" value="<%= request.getAttribute("password") %>"/><br/>

            Age: <input type="number" name="age" value="<%= request.getAttribute("age") %>"/><br/>

            Gender: <input type="text" name="gender" value="<%= request.getAttribute("gender") %>"/><br/>

            Role: <input type="text" name="role" value="<%= request.getAttribute("role") %>"/><br/>

            <button type="submit">Update</button>
        </form>
    </div>
</div>
</body>
</html>
