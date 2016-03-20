package nu.lansingcarworkshop.servlet.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    private boolean isAdminLoggedIn, isLoginSuccessful;
    private String action;
    private static final String adminUserName = "ADMIN";
    private static final String adminPassword = "ADMIN";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        if (action.equalsIgnoreCase("logout")) {
            logout(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        if (action.equalsIgnoreCase("login")) {
            login(request);
        }

        response.getWriter().print(isLoginSuccessful);
    }

    private void login(HttpServletRequest request) {
        String userNameInput = request.getParameter("username");
        String userPasswordInput = request.getParameter("password");

        isAdminLoggedIn = false;
        isLoginSuccessful = false;

        if (userNameInput.toUpperCase().equals(adminUserName) || userPasswordInput.toUpperCase().equals(adminPassword)) {
            isAdminLoggedIn = true;
            isLoginSuccessful = true;
        } else {
            isLoginSuccessful = true;
        }

        request.getSession().setAttribute("isLoginSuccessful", isLoginSuccessful);
        request.getSession().setAttribute("isAdminLoggedIn", isAdminLoggedIn);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("isAdminLoggedIn", false);
        request.getSession().setAttribute("isLoginSuccessful", false);
        response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
    }

}