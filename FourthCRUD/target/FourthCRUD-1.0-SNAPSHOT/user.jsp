<%@ page import="model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 29.05.2020
  Time: 4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</div>
<div>
    <% User user = (User) request.getAttribute("user");
        out.println("Login: " + user.getLogin() + "\n");
        out.println("Password: " + user.getPassword() + "\n");
        out.println("Age: " + user.getAge() + "\n");
        out.println("Gender: " + user.getGender() + "\n");
    %>
</div>
</body>
</html>
