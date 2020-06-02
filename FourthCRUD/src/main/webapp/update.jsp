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
            Login: <input type="text" name="edit_login" value="<%= request.getAttribute("edit_login") %>"/><br/>

            Password: <input type="password" name="edit_password" value="<%= request.getAttribute("edit_password") %>"/><br/>

            Age: <input type="number" name="edit_age" value="<%= request.getAttribute("edit_age") %>"/><br/>

            Gender: <input type="text" name="edit_gender" value="<%= request.getAttribute("edit_gender") %>"/><br/>

            Role: <input type="text" name="edit_role" value="<%= request.getAttribute("edit_role") %>"/><br/>

            <button type="submit">Update</button>
        </form>
    </div>
</div>
</body>
</html>
