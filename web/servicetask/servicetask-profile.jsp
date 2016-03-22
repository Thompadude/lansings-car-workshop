<%@ page import="nu.lansingcarworkshop.models.servicetask.ServiceTask" %>
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
    ServiceTask serviceTask = (ServiceTask) getServletConfig().getServletContext().getAttribute("currentServiceTask");
%>
<div class="container">
    <h1>
        Service Task
        <small><%=serviceTask.getId()%>
            <%if (isAdminLoggedIn) {%>
            &nbsp;
            <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTask.getId()%>&action=updateprofile"><span class="glyphicon glyphicon-edit"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=serviceTask.getId()%>"></span></a>
            <%}%>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Vehicle Owner</div>
                <div class="panel-body">
                    <a href="/ReadPersonServlet?personId=<%=serviceTask.getCustomer().getId()%>&action=viewprofile">
                        <%=serviceTask.getVehicle().getCustomer().getName()%>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Registration Plate</div>
                <div class="panel-body">
                    <a href="/ReadVehicleServlet?vehicleId=<%=serviceTask.getVehicle().getId()%>&action=viewprofile">
                        <%=serviceTask.getVehicle().getRegistrationPlate()%>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Make</div>
                <div class="panel-body"><%=serviceTask.getVehicle().getMake()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Responsible Technician</div>
                <div class="panel-body">
                    <%if (serviceTask.getResponsibleEmployee() != null) {%>
                    <a href="/ReadPersonServlet?personId=<%=serviceTask.getResponsibleEmployee().getId()%>&action=viewprofile">
                        <%=serviceTask.getResponsibleEmployee().getName()%>
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
                    <%=serviceTask.getAppointmentTime()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Notes</div>
                <div class="panel-body"><%=serviceTask.getNote()%>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>
