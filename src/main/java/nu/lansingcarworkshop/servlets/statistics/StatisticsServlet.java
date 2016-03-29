package nu.lansingcarworkshop.servlets.statistics;

import nu.lansingcarworkshop.servlets.helpers.GetRedirectUrl;
import nu.lansingcarworkshop.servlets.helpers.UserActions;
import nu.lansingcarworkshop.servlets.helpers.setcontext.SetStatisticsContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {

    private boolean actionsSuccessful;
    private SetStatisticsContext setStatisticsContext = new SetStatisticsContext();
    private UserActions userAction;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUserAction(request.getParameter("action"));

        actionsSuccessful = checkUserActionAndReactAccordingly();

        redirectToCorrectJsp(request, response);
    }

    private void setUserAction(String action) {
        if (action.equalsIgnoreCase("view-service-task-statistics")) {
            this.userAction = UserActions.VIEWSERVICETASKSTATISTICS;
        }
    }

    private boolean checkUserActionAndReactAccordingly() {
        if (userAction == UserActions.VIEWSERVICETASKSTATISTICS) {
            actionsSuccessful = setStatisticsContext.setServiceTaskStatistics(getServletContext());
        }
        return actionsSuccessful;
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (actionsSuccessful) {
            redirectToCorrectJspWhenActionsIsSuccessful(request, response);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/error.jsp");
        }
    }

    private void redirectToCorrectJspWhenActionsIsSuccessful(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GetRedirectUrl getRedirectUrl = new GetRedirectUrl();
        String redirectUrl = getRedirectUrl.getRedirectUrl(userAction, request);
        response.sendRedirect(redirectUrl);
    }

}