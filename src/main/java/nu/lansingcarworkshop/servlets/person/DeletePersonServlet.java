package nu.lansingcarworkshop.servlets.person;

import nu.lansingcarworkshop.services.person.DeletePerson;
import nu.lansingcarworkshop.servlets.helpers.setcontext.SetEntityClassesContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePersonServlet", urlPatterns = "/DeletePersonServlet")
public class DeletePersonServlet extends HttpServlet {

    SetEntityClassesContext setEntityClassesContext = new SetEntityClassesContext();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeletePersonInquired = Boolean.parseBoolean(request.getParameter("deletePerson"));
        int personId = Integer.parseInt(request.getParameter("personId"));

        boolean isAdminLoggedIn = (boolean) request.getSession().getAttribute("isAdminLoggedIn");
        if (isDeletePersonInquired && isAdminLoggedIn) {
            DeletePerson deletePerson = new DeletePerson();
            deletePerson.deletePersonById(personId);
            setEntityClassesContext.setServiceTasksLists(getServletContext());
        }

        response.sendRedirect("/ReadPersonServlet?&action=list-persons");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}