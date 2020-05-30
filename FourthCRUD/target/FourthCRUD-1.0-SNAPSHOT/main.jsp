<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>

<body>
<div>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</div>
<div>
    <div>
        <h2>Add user</h2>
    </div>
    <div>
        <form method="post" action="admin/add">
            Login: <input type="text" name="login"/><br/>

            Password: <input type="password" name="password"/><br/>

            Age: <input type="number" name="age"/><br/>

            Gender: <input type="text" name="gender"/><br/>

            Role: <input type="text" name="role"/><br/>

            <button type="submit">Add</button>
        </form>
    </div>
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
                <td>Gender</td>
                <td>Role</td>
                <td>Action</td>
            </tr>
            <% List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {
                        out.println("<tr>");
                        out.println("<td>" + user.getId() + "</td>");
                        out.println("<td>" + user.getLogin() + "</td>");
                        out.println("<td>" + user.getPassword() + "</td>");
                        out.println("<td>" + user.getAge() + "</td>");
                        out.println("<td>" + user.getGender() + "</td>");
                        out.println("<td>" + user.getRole() + "</td>");
                        out.println("<td> <a href=admin/delete?id=" + user.getId() + ">Delete</a> " +
                                "<a href=admin/edit?id=" + user.getId() + ">Edit</a>" +
                                "</td>");
                        out.println("</tr>");
                    }
                }
            %>
        </table>
    </div>
</div>

</body>
</html>
