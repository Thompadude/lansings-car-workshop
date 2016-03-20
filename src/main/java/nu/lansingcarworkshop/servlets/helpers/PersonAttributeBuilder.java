package nu.lansingcarworkshop.servlets.helpers;

import nu.lansingcarworkshop.models.person.*;

import javax.servlet.http.HttpServletRequest;

public class PersonAttributeBuilder {

    public ContactInformation createContactInformation(HttpServletRequest request) {
        String address = request.getParameter("person-address");
        String phone = request.getParameter("person-phone");
        return new ContactInformation(address, phone);
    }

    public Sex createSex(HttpServletRequest request) {
        String sexString = request.getParameter("person-sex");
        if (sexString.equals("male")) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

    public Role createRole(HttpServletRequest request) {
        String roleString = request.getParameter("person-role");
        if (roleString.equals("technician")) {
            return Role.TECHNICIAN;
        } else if (roleString.equals("specialist")) {
            return Role.SPECIALIST;
        }
        return null;
    }

    public void changeRole(Person personToUpdate, HttpServletRequest request) {
        if (personToUpdate instanceof Employee) {
            String roleString = request.getParameter("person-role");
            if (roleString.equals("technician")) {
                ((Employee) personToUpdate).setRole(Role.TECHNICIAN);
            } else {
                ((Employee) personToUpdate).setRole(Role.SPECIALIST);
            }
        }
    }

}