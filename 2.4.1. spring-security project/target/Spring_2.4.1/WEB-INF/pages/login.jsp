<%--
  Created by IntelliJ IDEA.
  User: Святослав
  Date: 25.11.2019
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/login">
    Login: <input name="j_username"/><br/><br/>
    Password: <input name="j_password"/><br/><br/>
    <input type="submit"/>
</form>

</body>
</html>
