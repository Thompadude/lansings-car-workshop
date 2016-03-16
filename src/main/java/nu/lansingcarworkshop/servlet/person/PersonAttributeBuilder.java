package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.entity.person.*;

import javax.servlet.http.HttpServletRequest;

public class PersonAttributeBuilder {

    protected ContactInformation createContactInformation(HttpServletRequest request) {
        String address = request.getParameter("person-address");
        String phone = request.getParameter("person-phone");
        return new ContactInformation(address, phone);
    }

    protected Sex createSex(HttpServletRequest request) {
        String sexString = request.getParameter("person-sex");
        if (sexString.equals("male")) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

    protected Role createRole(HttpServletRequest request) {
        String roleString = request.getParameter("person-role");
        if (roleString.equals("technician")) {
            return Role.TECHNICIAN;
        } else if (roleString.equals("manager")) {
            return Role.MANAGER;
        }
        return null;
    }

    protected void changeRole(Person personToUpdate, HttpServletRequest request) {
        if (personToUpdate instanceof Employee) {
            String roleString = request.getParameter("person-role");
            if (roleString.equals("technician")) {
                ((Employee) personToUpdate).setRole(Role.TECHNICIAN);
            } else {
                ((Employee) personToUpdate).setRole(Role.MANAGER);
            }
        }
    }

}