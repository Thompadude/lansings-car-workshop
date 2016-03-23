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

    boolean isDeleteServiceTaskInquired;
    int serviceTaskId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initializeVariablesFromGetRequest(request);

        boolean isAdminLoggedIn = (boolean) request.getSession().getAttribute("isAdminLoggedIn");
        if (isDeleteServiceTaskInquired && isAdminLoggedIn) {
            deleteServiceTask(serviceTaskId);
        }

        response.sendRedirect("/ReadServiceTaskServlet?&action=list-service-tasks");
    }

    private void initializeVariablesFromGetRequest(HttpServletRequest request) {
        isDeleteServiceTaskInquired = Boolean.parseBoolean(request.getParameter("deleteServiceTask"));
        serviceTaskId = Integer.parseInt(request.getParameter("service-task-id"));
    }

    private void deleteServiceTask(int serviceTaskId) {
        DeleteServiceTask deleteServiceTask = new DeleteServiceTask();
        deleteServiceTask.deleteServiceTaskById(serviceTaskId);
    }

}