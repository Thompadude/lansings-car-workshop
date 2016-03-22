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
<%
    List listOfTodaysServiceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfTodaysServiceTasks");
    List listOfNextMonthsServiceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfNextMonthsServiceTasks");
%>
<h1>Today's Service Bookings</h1>
<%if (listOfTodaysServiceTasks.size() > 0 && listOfTodaysServiceTasks != null) {%>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Vehicle Make</th>
        <th>Vehicle Registrationplate</th>
        <th>Vehicle Owner</th>
        <th>Responsible Technician</th>
        <th>Estimated Start Date</th>
        <%if (isAdminLoggedIn) {%>
        <th>Update</th>
        <%}%>
        <th>Info</th>
        <%if (isAdminLoggedIn) {%>
        <th>Remove</th>
        <%}%>
    </tr>
    </thead>
    <%
        for (Object serviceTask : listOfTodaysServiceTasks) {
            ServiceTask serviceTaskToDisplay = (ServiceTask) serviceTask;
    %>
    <tbody>
    <tr id="entry-<%=serviceTaskToDisplay.getId()%>">
        <td>
            <%=serviceTaskToDisplay.getVehicle().getMake()%>
        </td>
        <td>
            <%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
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
        <td>
            <%=serviceTaskToDisplay.getAppointmentTime()%>
        </td>
        <%if (isAdminLoggedIn) {%>
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
    </tbody>
</table>
<%
    }
} else {
%>
<h1>No services booked for today.</h1>
<%}%>
<h1>Next Month's Service Bookings</h1>
<%
    if (listOfNextMonthsServiceTasks.size() > 0 && listOfNextMonthsServiceTasks != null) {
%>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Vehicle Make</th>
        <th>Vehicle Registrationplate</th>
        <th>Vehicle Owner</th>
        <th>Responsible Technician</th>
        <th>Estimated Start Date</th>
        <%if (isAdminLoggedIn) {%>
        <th>Update</th>
        <%}%>
        <th>Info</th>
        <%if (isAdminLoggedIn) {%>
        <th>Remove</th>
        <%}%>
    </tr>
    </thead>
    <%
        for (Object serviceTask : listOfNextMonthsServiceTasks) {
            ServiceTask serviceTaskToDisplay = (ServiceTask) serviceTask;
    %>
    <tbody>
    <tr id="entry-<%=serviceTaskToDisplay.getId()%>">
        <td>
            <%=serviceTaskToDisplay.getVehicle().getMake()%>
        </td>
        <td>
            <%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
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
        <td>
            <%=serviceTaskToDisplay.getAppointmentTime()%>
        </td>
        <%if (isAdminLoggedIn) {%>
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
    </tbody>
</table>
<%
    }
} else {
%>
<h1>No services booked for next month.</h1>
<%}%>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>