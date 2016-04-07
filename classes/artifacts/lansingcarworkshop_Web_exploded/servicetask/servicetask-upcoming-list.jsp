<%@ page import="nu.lansingcarworkshop.models.servicetask.ServiceTask" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/stylesheet.css">
    <title>LCW &mdash; Service List</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<%
    List listOfTodaysServiceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfTodaysServiceTasks");
    List listOfNextMonthsServiceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfNextMonthsServiceTasks");
%>
<div class="container-fluid">
    <h1>Today's Service Bookings</h1>
    <%if (listOfTodaysServiceTasks.size() > 0 && listOfTodaysServiceTasks != null) {%>
    <div class="table-responsive">
        <table class="table table-striped">
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
                for (Object serviceTask : listOfTodaysServiceTasks) {
                    ServiceTask serviceTaskToDisplay = (ServiceTask) serviceTask;
            %>
            <tr id="entry-<%=serviceTaskToDisplay.getId()%>">
                <td>
                    <%=serviceTaskToDisplay.getVehicle().getMake()%>
                </td>
                <td>
                    <%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
                </td>
                <td>
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getVehicle().getCustomer().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getVehicle().getCustomer().getName()%>
                    </a>
                </td>
                <td>
                    <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
                    </a>
                    <%} else {%>
                    <span class="no-one-assigned">No one assigned</span>
                    <%}%>
                </td>
                <td>
                    <%=serviceTaskToDisplay.getAppointmentTime()%>
                </td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <%if (serviceTaskToDisplay.isCompleted()) {%>
                    <span class="completed">Completed</span>
                    <%} else {%>
                    <span class="incomplete">Not completed</span>
                    <%}%>
                </td>
                <td>
                    <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=update-service-task"><span class="glyphicon glyphicon-edit"></span></a>
                </td>
                <%}%>
                <td>
                    <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=view-service-task-profile"><span class="glyphicon glyphicon-info-sign"></span></a>
                </td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=serviceTaskToDisplay.getId()%>"></span></a>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
    <%
    } else {
    %>
    <h3>No services booked for today.</h3>
    <%}%>
    <h1>Next Month's Service Bookings</h1>
    <%
        if (listOfNextMonthsServiceTasks.size() > 0 && listOfNextMonthsServiceTasks != null) {
    %>
    <div class="table-responsive">
        <table class="table table-striped">
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
                for (Object serviceTask : listOfNextMonthsServiceTasks) {
                    ServiceTask serviceTaskToDisplay = (ServiceTask) serviceTask;
            %>
            <tr id="entry-<%=serviceTaskToDisplay.getId()%>">
                <td>
                    <%=serviceTaskToDisplay.getVehicle().getMake()%>
                </td>
                <td>
                    <%=serviceTaskToDisplay.getVehicle().getRegistrationPlate()%>
                </td>
                <td>
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getVehicle().getCustomer().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getVehicle().getCustomer().getName()%>
                    </a>
                </td>
                <td>
                    <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
                    </a>
                    <%} else {%>
                    <span class="no-one-assigned">No one assigned</span>
                    <%}%>
                </td>
                <td>
                    <%=serviceTaskToDisplay.getAppointmentTime()%>
                </td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <%if (serviceTaskToDisplay.isCompleted()) {%>
                    <span class="completed">Completed</span>
                    <%} else {%>
                    <span class="incomplete">Not completed</span>
                    <%}%>
                </td>
                <td>
                    <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=update-service-task"><span class="glyphicon glyphicon-edit"></span></a>
                </td>
                <%}%>
                <td>
                    <a href="/ReadServiceTaskServlet?serviceTaskId=<%=serviceTaskToDisplay.getId()%>&action=view-service-task-profile"><span class="glyphicon glyphicon-info-sign"></span></a>
                </td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=serviceTaskToDisplay.getId()%>"></span></a>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
    <%
    } else {
    %>
    <h3>No services booked for next month.</h3>
    <%}%>
</div>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>