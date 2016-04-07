<%@ page import="nu.lansingcarworkshop.models.servicetask.ServiceTask" %>
<%@ page import="nu.lansingcarworkshop.models.vehicle.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Vehicle Profile</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<%
    Vehicle vehicleToDisplay = (Vehicle) getServletConfig().getServletContext().getAttribute("currentVehicle");
    List serviceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfServiceTasks");
%>
<div class="container">
    <h1>
        <%=vehicleToDisplay.getMake()%>
        <small><%=vehicleToDisplay.getId()%>
            <%if (isAdminLoggedIn) {%>
            &nbsp;
            <a href="/ReadVehicleServlet?vehicleId=<%=vehicleToDisplay.getId()%>&action=create-service-task"><span class="glyphicon glyphicon-wrench"></span></a>
            <a href="/ReadVehicleServlet?vehicleId=<%=vehicleToDisplay.getId()%>&action=update-vehicle"><span class="glyphicon glyphicon-edit"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=vehicleToDisplay.getId()%>"></span></a>
            <%}%>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Owner</div>
                <div class="panel-body"><a href="/ReadPersonServlet?personId=<%=vehicleToDisplay.getCustomer().getId()%>&action=view-person-profile"><%=vehicleToDisplay.getCustomer().getName()%>
                </a></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Registration Plate</div>
                <div class="panel-body"><%=vehicleToDisplay.getRegistrationPlate()%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Make</div>
                <div class="panel-body"><%=vehicleToDisplay.getMake()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Model Year</div>
                <div class="panel-body"><%=vehicleToDisplay.getModelYear().getYear()%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Fuel Type</div>
                <div class="panel-body"><%=vehicleToDisplay.getFuel()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <%if (serviceTasks.size() > 0) {%>
            <h1>Services Booked</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Time</th>
                    <th>Technician</th>
                    <th>View Details</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Object serviceTask : serviceTasks) {
                        ServiceTask serviceTaskToDisplay = ((ServiceTask) serviceTask);
                %>
                <tr>
                    <td><%=serviceTaskToDisplay.getAppointmentTime()%>
                    <td>
                        <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
                        <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
                        </a>
                        <%} else {%>
                        No one assigned
                        <%}%>
                    </td>
                    <td>
                        <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=view-service-task-profile"><span class="glyphicon glyphicon-info-sign"></span></a></td>
                    <td>
                </tr>
                <%
                    }
                } else {
                %>
                <h3>No services planned.</h3>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="../js/vehicle-delete.js"></script>
</body>
</html>