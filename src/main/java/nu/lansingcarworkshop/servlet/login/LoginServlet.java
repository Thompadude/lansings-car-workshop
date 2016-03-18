package nu.lansingcarworkshop.servlet.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userNameInput = request.getParameter("username");
        String userPasswordInput = request.getParameter("password");

        boolean isAdminLoggedIn = true;
        boolean loginSuccessful = true;

        final String adminUserName = "ADMIN";
        final String adminPassword = "ADMIN";

        if (!userNameInput.toUpperCase().equals(adminUserName) || !userPasswordInput.toUpperCase().equals(adminPassword)) {
            isAdminLoggedIn = false;
            loginSuccessful = false;
        }

        getServletContext().setAttribute("isAdminLoggedIn", isAdminLoggedIn);

        response.getWriter().print(loginSuccessful);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}