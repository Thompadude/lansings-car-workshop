package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.entity.person.ContactInformation;
import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.person.Sex;
import nu.lansingcarworkshop.service.person.ReadPerson;
import nu.lansingcarworkshop.service.person.UpdatePerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "UpdatePersonServlet")
public class UpdatePersonServlet extends HttpServlet {

    private PersonAttributeBuilder personAttributeBuilder = new PersonAttributeBuilder();
    private Person personWithUpdatedAttributes;
    private String name;
    private ContactInformation contactInformation;
    private String birthdayString;
    private LocalDate birthday;
    private Sex sex;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int personId = Integer.parseInt(request.getParameter("personid"));
        ReadPerson readPerson = new ReadPerson();
        personWithUpdatedAttributes = readPerson.getPersonById(personId);

        initializeVariablesFromPostRequest(request);

        setNewAttributes(request);

        UpdatePerson updatePerson = new UpdatePerson();
        updatePerson.updatePerson(personWithUpdatedAttributes);

        response.sendRedirect("/ReadPersonServlet?personId=" + personWithUpdatedAttributes.getId() + "&action=viewprofile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        name = request.getParameter("person-name");
        contactInformation = personAttributeBuilder.createContactInformation(request);
        birthdayString = request.getParameter("person-birthday");
        birthday = LocalDate.parse(birthdayString);
        sex = personAttributeBuilder.createSex(request);
    }

    private void setNewAttributes(HttpServletRequest request) {
        personWithUpdatedAttributes.setName(name);
        personWithUpdatedAttributes.setContactInformation(contactInformation);
        personWithUpdatedAttributes.setBirthdate(birthday);
        personWithUpdatedAttributes.setSex(sex);
        personAttributeBuilder.changeRole(personWithUpdatedAttributes, request);
    }

}
