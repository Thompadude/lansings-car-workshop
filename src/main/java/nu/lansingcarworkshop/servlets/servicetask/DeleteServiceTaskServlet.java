package nu.lansingcarworkshop.servlets.servicetask;

import nu.lansingcarworkshop.services.servicetask.DeleteServiceTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServiceTaskServlet")

public class DeleteServiceTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeleteServiceTaskInquired = Boolean.parseBoolean(request.getParameter("deleteServiceTask"));
        int serviceTaskId = Integer.parseInt(request.getParameter("serviceTaskId"));

        boolean isAdminLoggedIn = (boolean) request.getSession().getAttribute("isAdminLoggedIn");
        if (isDeleteServiceTaskInquired && isAdminLoggedIn) {
            DeleteServiceTask deleteServiceTask = new DeleteServiceTask();
            deleteServiceTask.deleteServiceTaskById(serviceTaskId);
        }

        response.sendRedirect("/ReadServiceTaskServlet?&action=list-service-tasks");
    }

}