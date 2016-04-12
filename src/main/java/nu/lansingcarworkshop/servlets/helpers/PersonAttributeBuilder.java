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

        if (sexString == null) {
            return Sex.UNKNOWN;
        } else if (sexString.equals("male")) {
            return Sex.MALE;
        } else if (sexString.equals("female")) {
            return Sex.FEMALE;
        }
        return Sex.UNKNOWN;
    }

    public Role createRole(HttpServletRequest request) {
        String roleString = request.getParameter("person-role");
        if (roleString != null && !(roleString.equals(""))) {
            if (roleString.equalsIgnoreCase("TECHNICIAN")) {
                return Role.TECHNICIAN;
            } else if (roleString.equalsIgnoreCase("SPECIALIST")) {
                return Role.SPECIALIST;
            }
        }
        return Role.NONE;
    }

    public void changeRole(Person personToUpdate, Role role) {
        if (personToUpdate instanceof Employee) {
            if (role == Role.TECHNICIAN) {
                ((Employee) personToUpdate).setRole(Role.TECHNICIAN);
            } else {
                ((Employee) personToUpdate).setRole(Role.SPECIALIST);
            }
        }
    }

}