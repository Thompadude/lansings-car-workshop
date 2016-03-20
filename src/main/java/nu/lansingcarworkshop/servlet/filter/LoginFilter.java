package nu.lansingcarworkshop.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/person/*", "/servicetask/*", "/vehicle/*"})
public class LoginFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession httpSession = httpServletRequest.getSession(false);

        boolean userIsLoggedIn = httpSession != null && httpServletRequest.getSession().getAttribute("isLoginSuccessful") != null;
        boolean isLoginSuccessful = false;

        if (userIsLoggedIn) {
            isLoginSuccessful = (boolean) httpServletRequest.getSession().getAttribute("isLoginSuccessful");
        }

        if (isLoginSuccessful) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}