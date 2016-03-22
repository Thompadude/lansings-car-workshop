<%@ page import="nu.lansingcarworkshop.models.servicetask.ServiceTask" %>
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
<h1>All Service Bookings</h1>

<div class="table-responsive">
    <table class="table table-striped">
        <%
            List serviceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfServiceTasks");

            if (serviceTasks.size() > 0 && serviceTasks != null) {
        %>
        <thead>
        <tr>
            <th>Vehicle Make</th>
            <th>Vehicle Registration's Plate</th>
            <th>Vehicle Owner</th>
            <th>Responsible Technician</th>
            <th>Estimated Start Date</th>
            <%if (isAdminLoggedIn) {%>
            <th>Status</th>
            <th>Update</th>
            <%}%>
            <th>Info</th>
            <%if (isAdminLoggedIn) {%>
            <th>Remove</th>
            <%}%>
        </tr>
        </thead>
        <tbody>
        <%
            for (Object serviceTask : serviceTasks) {
                ServiceTask serviceTaskToDisplay = (ServiceTask) serviceTask;
        %>
        <tr id="entry-<%=serviceTaskToDisplay.getId()%>">
            <td><%=serviceTaskToDisplay.getVehicle().getMake()%>
            </td>
            <td><%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
            </td>
            <td>
                <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getVehicle().getCustomer().getId()%>&action=viewprofile"><%=serviceTaskToDisplay.getVehicle().getCustomer().getName()%>
                </a>
            </td>
            <td>
                <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
                <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>&action=viewprofile"><%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
                </a>
                <%} else {%>
                No one assigned
                <%}%>
            </td>
            <td><%=serviceTaskToDisplay.getAppointmentTime()%>
            </td>
            <%if (isAdminLoggedIn) {%>
            <td>
                <%if (serviceTaskToDisplay.isCompleted()) {%>
                Completed
                <%} else {%>
                Not completed
                <%}%>
            </td>
            <td>
                <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=updateprofile"><span class="glyphicon glyphicon-edit"></span></a>
            </td>
            <%}%>
            <td>
                <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=viewprofile"><span class="glyphicon glyphicon-info-sign"></span></a>
            </td>
            <%if (isAdminLoggedIn) {%>
            <td>
                <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=serviceTaskToDisplay.getId()%>"></span></a>
            </td>
            <%}%>
        </tr>
        <%
            }
        } else {
        %>
        <h1>No services booked. <a href="/ReadVehicleServlet?action=listvehicles">Go back to list of vehicles.</a></h1>
        <%}%>
        </tbody>
    </table>
</div>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>