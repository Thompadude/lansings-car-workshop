package nu.lansingcarworkshop.servlets.helpers;

import javax.servlet.http.HttpServletRequest;

public class GetRedirectUrl {

    public String getRedirectUrl(UserActions action, HttpServletRequest request) {
        String baseUrl = request.getContextPath();

        switch (action) {
            case ADDVEHICLETOPERSON:
                return baseUrl + "/person/add-vehicle-to-person.jsp";
            case VIEWPERSONLIST:
                return baseUrl + "/person/persons-edit-delete.jsp";
            case VIEWPERSON:
                return baseUrl + "/person/person-profile.jsp";
            case UPDATEPERSON:
                return baseUrl + "/person/person-update.jsp";
            case VIEWVEHICLELIST:
                return baseUrl + "/vehicle/vehicles-list.jsp";
            case VIEWVEHICLE:
                return baseUrl + "/vehicle/vehicle-profile.jsp";
            case UPDATEVEHICLE:
                return baseUrl + "/vehicle/vehicle-update.jsp";
            case CREATESERVICETASK:
                return baseUrl + "/servicetask/service-task-create.jsp";
            case VIEWSERVICETASKLIST:
                return baseUrl + "/servicetask/servicetask-list.jsp";
            case VIEWSERVICETASK:
                return baseUrl + "/servicetask/servicetask-profile.jsp";
            case UPDATESERVICETASK:
                return baseUrl + "/servicetask/servicetask-update.jsp";
            case VIEWUPCOMINGSERVICETASKS:
                return baseUrl + "/servicetask/servicetask-upcoming-list.jsp";
            case VIEWSERVICETASKSTATISTICS:
                return baseUrl + "/servicetask/service-task-statistics.jsp";
            default:
                return baseUrl + "/error.jsp";
        }
    }

}