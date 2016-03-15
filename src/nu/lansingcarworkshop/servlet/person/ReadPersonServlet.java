package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.person.ReadPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadPersonServlet")
public class ReadPersonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person personToDisplay = getPersonByParameter(request);

        getServletContext().setAttribute("currentPerson", personToDisplay);
        response.sendRedirect("person/person-profile.jsp");
    }

    private Person getPersonByParameter(HttpServletRequest request) {
        int personId = Integer.parseInt(request.getParameter("personId"));
        ReadPerson readPersonById = new ReadPerson();
        return readPersonById.getPersonById(personId);
    }

}