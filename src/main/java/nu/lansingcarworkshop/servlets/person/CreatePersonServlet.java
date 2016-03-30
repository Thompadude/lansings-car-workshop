package nu.lansingcarworkshop.servlets.person;

import nu.lansingcarworkshop.models.person.*;
import nu.lansingcarworkshop.services.person.CreatePerson;
import nu.lansingcarworkshop.servlets.helpers.PersonAttributeBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CreatePersonServlet", urlPatterns = "/CreatePersonServlet")
public class CreatePersonServlet extends javax.servlet.http.HttpServlet {

    private PersonAttributeBuilder personAttributeBuilder = new PersonAttributeBuilder();
    private String name;
    private ContactInformation contactInformation;
    private LocalDate birthday;
    private Sex sex;
    private Role role;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        initializeVariablesFromPostRequest(request);

        createAndAddPersonToDatabase();

        response.getWriter().print(" created and added to database.");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        name = request.getParameter("person-name");
        contactInformation = personAttributeBuilder.createContactInformation(request);
        birthday = LocalDate.parse(request.getParameter("person-birthday"));
        sex = personAttributeBuilder.createSex(request);
        role = personAttributeBuilder.createRole(request);
    }

    private void createAndAddPersonToDatabase() {
        Person newPerson = checkTypeAndCreateNewPerson();
        CreatePerson createPerson = new CreatePerson();
        createPerson.createPerson(newPerson);
    }

    private Person checkTypeAndCreateNewPerson() {
        if (role == null) {
            return new Customer(name, contactInformation, birthday, sex);
        } else {
            return new Employee(name, contactInformation, birthday, sex, role);
        }
    }

}