package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.entity.person.*;
import nu.lansingcarworkshop.service.person.CreatePerson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CreatePersonServlet")
public class CreatePersonServlet extends javax.servlet.http.HttpServlet {

    private PersonAttributeBuilder personAttributeBuilder = new PersonAttributeBuilder();
    private String name;
    private ContactInformation contactInformation;
    private LocalDate birthday;
    private Sex sex;
    private Role role;
    private Person newPerson;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        initializeVariablesFromPostRequest(request);

        createAndAddPersonToDatabase();

        response.getWriter().print("created and added to database.");
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

    private Person checkTypeAndCreateNewPerson() {
        if (role == null) {
            return new Customer(name, contactInformation, birthday, sex);
        } else {
            return new Employee(name, contactInformation, birthday, sex, role);
        }
    }

    private void createAndAddPersonToDatabase() {
        newPerson = checkTypeAndCreateNewPerson();
        CreatePerson createPerson = new CreatePerson();
        createPerson.createPerson(newPerson);
    }

}