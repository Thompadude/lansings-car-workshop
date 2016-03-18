<%@ page import="nu.lansingcarworkshop.entity.servicetask.ServiceTask" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Service Task</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    ServiceTask serviceTaskToDisplay = (ServiceTask) getServletConfig().getServletContext().getAttribute("currentServiceTask");
%>
<div class="container">
    <h1>
        Service Task
        <small><%=serviceTaskToDisplay.getId()%>
            &nbsp;
            <a href="servicetask-update.jsp?serviceTaskId=<%=serviceTaskToDisplay.getId()%>"><span
                    class="glyphicon glyphicon-edit"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden"
                                                                        value="<%=serviceTaskToDisplay.getId()%>"></span></a>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Vehicle Owner</div>
                <div class="panel-body"><a
                        href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getVehicle().getCustomer().getId()%>"><%=serviceTaskToDisplay.getVehicle().getCustomer().getName()%>
                </a></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Registration Plate</div>
                <div class="panel-body">
                    <a href="/ReadVehicleServlet?vehicleId=<%=serviceTaskToDisplay.getVehicle().getId()%>">
                        <%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Make</div>
                <div class="panel-body"><%=serviceTaskToDisplay.getVehicle().getMake()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Responsible Technician</div>
                <div class="panel-body">
                    <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>">
                        <%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
                    </a>
                    <%} else {%>
                    No one assigned
                    <%}%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Estimated Start Date</div>
                <div class="panel-body">
                    <%=serviceTaskToDisplay.getAppointmentTime()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Notes</div>
                <div class="panel-body"><%=serviceTaskToDisplay.getNote()%>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>
