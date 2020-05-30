package servlets;

import model.User;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
    private Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = null;
        try {
            user = service.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("age", user.getAge());
        req.setAttribute("gender", user.getGender());
        req.setAttribute("role", user.getRole());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String role = req.getParameter("role");

        try {
            service.updateUser(id, login, password, age, gender, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/admin");
    }
}
