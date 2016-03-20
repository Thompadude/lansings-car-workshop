<%@ page import="nu.lansingcarworkshop.entity.servicetask.ServiceTask" %>
<%@ page import="nu.lansingcarworkshop.entity.vehicle.Vehicle" %>
<%@ page import="nu.lansingcarworkshop.service.servicetask.ReadServiceTask" %>
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
    boolean isAdminLoggedIn = (boolean) session.getAttribute("isAdminLoggedIn");

    Vehicle vehicleToDisplay = (Vehicle) getServletConfig().getServletContext().getAttribute("currentVehicle");
%>

<div class="container">
    <h1>
        <%=vehicleToDisplay.getMake()%>
        <small><%=vehicleToDisplay.getId()%>
            <%if (isAdminLoggedIn) {%>
            &nbsp;
            <a href="../servicetask/servicetask-create.jsp?vehicleId=<%=vehicleToDisplay.getId()%>"><span class="glyphicon glyphicon-wrench"></span></a>
            <a href="vehicle-update.jsp?vehicleId=<%=vehicleToDisplay.getId()%>"><span class="glyphicon glyphicon-edit"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=vehicleToDisplay.getId()%>"></span></a>
            <%}%>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Owner</div>
                <div class="panel-body"><a
                        href="/ReadPersonServlet?personId=<%=vehicleToDisplay.getCustomer().getId()%>"><%=vehicleToDisplay.getCustomer().getName()%>
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
                    ReadServiceTask readServiceTask = new ReadServiceTask();
                    List serviceTasks = readServiceTask.getAllServiceTasksByCarId(vehicleToDisplay);
                    //TODO add more methods for other types of vehicles in list query.
                    for (Object serviceTask : serviceTasks) {
                %>
                <tr>
                    <td>
                        <%=((ServiceTask) serviceTask).getAppointmentTime()%>
                    </td>
                    <td>
                        <a href="/ReadPersonServlet?personId=<%=((ServiceTask) serviceTask).getResponsibleEmployee().getId()%>">
                            <%=((ServiceTask) serviceTask).getResponsibleEmployee().getName()%>
                        </a>
                    </td>
                    <td><a href="/ReadServiceTaskServlet?serviceTaskId=<%=((ServiceTask) serviceTask).getId()%>"><span
                            class="glyphicon glyphicon-info-sign"></span></a></td>
                    <td>
                </tr>
                <%
                    }

                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="../js/vehicle-delete.js"></script>
</body>
</html>
