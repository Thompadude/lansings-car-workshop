package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.service.person.DeletePerson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePersonServlet")
public class DeletePersonServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeletePersonInquired = Boolean.parseBoolean(request.getParameter("deletePerson"));
        int personId = Integer.parseInt(request.getParameter("personId"));

        if (isDeletePersonInquired) {
            DeletePerson deletePerson = new DeletePerson();
            deletePerson.deletePersonById(personId);
        }

        response.sendRedirect("/ReadPersonServlet?&action=listpersons");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}