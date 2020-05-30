package servlets;

import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String role = req.getParameter("role");

        Service service = Service.getInstance();

        try {
            service.addUser(login, password, age, gender, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/admin");
    }
}
