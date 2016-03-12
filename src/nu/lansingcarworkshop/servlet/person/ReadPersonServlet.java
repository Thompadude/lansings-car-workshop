package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.person.GetPersons;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReadPersonServlet")
public class ReadPersonServlet extends HttpServlet {

    Person personToDisplay;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = Integer.parseInt(request.getParameter("personId"));

        GetPersons getPersons = new GetPersons();
        List<Person> persons = getPersons.getAllPersons();

        for (Person person : persons) {
            if (person.getId() == personId)
                personToDisplay = person;
        }

        getServletContext().setAttribute("currentPerson", personToDisplay);
        response.sendRedirect("person-profile.jsp");
    }

}