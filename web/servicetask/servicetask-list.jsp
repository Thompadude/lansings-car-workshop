<%@ page import="nu.lansingcarworkshop.entity.servicetask.ServiceTask" %>
<%@ page import="nu.lansingcarworkshop.service.servicetask.ReadServiceTask" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Service List</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    ReadServiceTask readServiceTask = new ReadServiceTask();
    List serviceTasks = readServiceTask.getAllServiceTasks();

    if (serviceTasks.size() > 0 && serviceTasks != null) {
%>
<h1>All Service Bookings</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Vehicle Make</th>
        <th>Vehicle Registrationplate</th>
        <th>Vehicle Owner</th>
        <th>Responsible Technician</th>
        <th>Estimated Start Date</th>
        <th>Update</th>
        <th>Info</th>
        <th>Remove</th>
    </tr>
    </thead>
    <%
        for (Object serviceTask : serviceTasks) {
            ServiceTask serviceTaskToDisplay = (ServiceTask) serviceTask;
    %>
    <tbody>
    <tr id="entry-<%=serviceTaskToDisplay.getId()%>">
        <td><%=serviceTaskToDisplay.getVehicle().getMake()%>
        </td>
        <td><%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
        </td>
        <td>
            <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getVehicle().getCustomer().getId()%>">
                <%=serviceTaskToDisplay.getVehicle().getCustomer().getName()%>
            </a>
        </td>
        <td>
        <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
            <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>">
                <%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
            </a>
        <%} else {%>
            No one assigned
        <%}%>
        </td>
        <td>
            <%=serviceTaskToDisplay.getAppointmentTime()%>
        </td>
        <td>
            <a href="#"><span class="glyphicon glyphicon-edit"></span></a>
        </td>
        <td><a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>"><span
                class="glyphicon glyphicon-info-sign"></span></a></td>
        <td>
            <a href="#">
            <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=serviceTaskToDisplay.getId()%>">
                </span>
            </a>
        </td>
    </tr>
    </tbody>
    <%
        }
    } else {
    %>
    <h1>No services booked. <a href="../vehicle/vehicles-list.jsp">Go back to list of vehicles.</a></h1>
    <%}%>
</table>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>