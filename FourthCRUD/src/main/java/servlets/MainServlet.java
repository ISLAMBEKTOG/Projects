package servlets;

import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Service service = Service.getInstance();

        if (service.validateUser(login, password)) {
            try {
                String role = service.getRoleByLogin(login);

                HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }

                HttpSession newSession = req.getSession();
                newSession.setMaxInactiveInterval(60);

                Cookie role_cookie = new Cookie("client_role", role);
                Cookie login_cookie = new Cookie("client_login", login);
                resp.addCookie(role_cookie);
                resp.addCookie(login_cookie);

                if (role.equalsIgnoreCase("admin")) {
                    resp.sendRedirect("/admin");
                } else if (role.equalsIgnoreCase("user")) {
                    resp.sendRedirect("/user");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}
