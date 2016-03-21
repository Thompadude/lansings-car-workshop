package nu.lansingcarworkshop.servlets.helpers;

import javax.servlet.http.HttpServletRequest;

public class GetRedirectUrl {

    private String baseUrl;

    public String getReadPersonServletRedirectUrl(String action, HttpServletRequest request){
        baseUrl = request.getContextPath();

        switch (action) {
            case "addvehicle":
                return baseUrl + "/person/person-addvehicle.jsp";
            case "listpersons":
                return baseUrl + "/person/persons-edit-delete.jsp";
            case "viewprofile":
                return baseUrl + "/person/person-profile.jsp";
            case "updateprofile":
                return baseUrl + "/person/person-update.jsp";
            default:
                return baseUrl + "/error.jsp";
        }
    }

    public String getReadServiceTaskServletRedirectUrl(String action, HttpServletRequest request) {
        baseUrl = request.getContextPath();

        switch (action) {
            case "listservicetasks":
                return baseUrl + "/servicetask/servicetask-list.jsp";
            case "viewprofile":
                return baseUrl + "/servicetask/servicetask-profile.jsp";
            case "updateprofile":
                return baseUrl + "/servicetask/servicetask-update.jsp";
            case "listupcomingservicetasks":
                return baseUrl + "/servicetask/servicetask-upcoming-list.jsp";
            default:
                return baseUrl + "/error.jsp";
        }
    }

    public String getReadVehicleServletRedirectUrl(String action, HttpServletRequest request) {
        baseUrl = request.getContextPath();

        switch (action) {
            case "listvehicles":
                return baseUrl + "/vehicle/vehicles-list.jsp";
            case "viewprofile":
                return baseUrl + "/vehicle/vehicle-profile.jsp";
            case "updateprofile":
                return baseUrl + "/vehicle/vehicle-update.jsp";
            case "createservicetask":
                return baseUrl + "/servicetask/servicetask-create.jsp";
            default:
                return baseUrl + "/error.jsp";
        }
    }

}
