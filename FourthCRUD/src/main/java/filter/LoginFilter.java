package filter;

import service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Service service = Service.getInstance();

        HttpSession session = req.getSession();

        if (service.validateUser(login, password)) {
            try {
                String role = service.getRoleByLogin(login);
                session.setAttribute("client_role", role);
                session.setAttribute("client_login", login);

                if (role.equals("admin")) {
                    resp.sendRedirect(req.getContextPath() + "/admin");
                } else if (role.equals("user")) {
                    resp.sendRedirect(req.getContextPath() + "/user");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            filterChain.doFilter(req, resp);
            return;
        }
    }
}
