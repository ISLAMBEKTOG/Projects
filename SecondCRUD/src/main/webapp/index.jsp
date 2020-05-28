<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>

<body>
<div>
    <div>
        <h2>Add user</h2>
    </div>
    <div>
        <form method="post" action="add">
            Name: <input type="text" name="name"/><br/>

            Age: <input type="number" name="age"/><br/>

            Gender: <input type="text" name="gender"/><br/>

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
                <td>Name</td>
                <td>Age</td>
                <td>Gender</td>
                <td>Action</td>
            </tr>
            <% List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {
                        out.println("<tr>");
                        out.println("<td>" + user.getId() + "</td>");
                        out.println("<td>" + user.getName() + "</td>");
                        out.println("<td>" + user.getAge() + "</td>");
                        out.println("<td>" + user.getGender() + "</td>");
                        out.println("<td> <a href=delete?id=" + user.getId() + ">Delete</a> " +
                                "<a href=edit?id=" + user.getId() + ">Edit</a>" +
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