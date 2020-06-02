package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        if (session == null) {
            res.sendRedirect("/");
        } else {

            String role = (String) session.getAttribute("client_role");
            if (role != null) {
                if (role.equals("admin")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    res.sendRedirect("/");
                }
            } else {
                res.sendRedirect("/");
            }
        }
    }
}
