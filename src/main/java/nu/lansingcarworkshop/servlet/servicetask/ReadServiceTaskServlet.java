package nu.lansingcarworkshop.servlet.servicetask;

import nu.lansingcarworkshop.entity.servicetask.ServiceTask;
import nu.lansingcarworkshop.service.servicetask.ReadServiceTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadServiceTaskServlet")
public class ReadServiceTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceTask serviceTask = getServiceTaskByParameter(request);

        getServletContext().setAttribute("currentServiceTask", serviceTask);
        response.sendRedirect("servicetask/servicetask-profile.jsp");
    }

    private ServiceTask getServiceTaskByParameter(HttpServletRequest request) {
        int serviceTaskId = Integer.parseInt(request.getParameter("serviceTaskId"));
        ReadServiceTask readServiceTask = new ReadServiceTask();
        return readServiceTask.getServiceTaskById(serviceTaskId);
    }

}
