package servlets;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("name", user.getName());
        req.setAttribute("age", user.getAge());
        req.setAttribute("gender", user.getGender());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");

        try {
            userService.updateUser(id, name, age, gender);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/");
    }
}
