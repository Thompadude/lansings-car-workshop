package nu.lansingcarworkshop.servlets.person;

import nu.lansingcarworkshop.models.person.ContactInformation;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.person.Role;
import nu.lansingcarworkshop.models.person.Sex;
import nu.lansingcarworkshop.services.person.ReadPerson;
import nu.lansingcarworkshop.services.person.UpdatePerson;
import nu.lansingcarworkshop.servlets.helpers.PersonAttributeBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "UpdatePersonServlet", urlPatterns = "/UpdatePersonServlet")
public class UpdatePersonServlet extends HttpServlet {

    private PersonAttributeBuilder personAttributeBuilder = new PersonAttributeBuilder();
    private Person personWithUpdatedAttributes;
    private String name;
    private ContactInformation contactInformation;
    private LocalDate birthday;
    private Role role;
    private Sex sex;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String personIdString = request.getParameter("personid");

        int personId = Integer.parseInt(personIdString);
        ReadPerson readPerson = new ReadPerson();
        personWithUpdatedAttributes = readPerson.getPersonById(personId);

        initializeVariablesFromPostRequest(request);

        setNewAttributes();

        UpdatePerson updatePerson = new UpdatePerson();
        updatePerson.updatePerson(personWithUpdatedAttributes);

        response.sendRedirect("/ReadPersonServlet?personId=" + personWithUpdatedAttributes.getId() + "&action=view-person-profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        name = request.getParameter("person-name");
        contactInformation = personAttributeBuilder.createContactInformation(request);
        String birthdayString = request.getParameter("person-birthday");
        birthday = LocalDate.parse(birthdayString);
        sex = personAttributeBuilder.createSex(request);
        role = personAttributeBuilder.createRole(request);
    }

    private void setNewAttributes() {
        personWithUpdatedAttributes.setName(name);
        personWithUpdatedAttributes.setContactInformation(contactInformation);
        personWithUpdatedAttributes.setBirthdate(birthday);
        personWithUpdatedAttributes.setSex(sex);
        personAttributeBuilder.changeRole(personWithUpdatedAttributes, role);
    }

}
