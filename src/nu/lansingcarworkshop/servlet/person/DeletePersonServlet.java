package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.service.person.DeletePerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePersonServlet")
public class DeletePersonServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = Integer.parseInt(request.getParameter("personId"));

        DeletePerson deletePerson = new DeletePerson();
        deletePerson.deletePerson(personId);

        // TODO remake this to a ajax call.
        response.sendRedirect("persons-edit-delete.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}