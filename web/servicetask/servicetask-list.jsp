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
<div class="container-fluid">
    <h1>All Service Bookings</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <%
                List serviceTasks = (List) getServletConfig().getServletContext().getAttribute("listOfServiceTasks");

                boolean isTaskMissingEmployee = false;

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
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getVehicle().getCustomer().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getVehicle().getCustomer().getName()%>
                    </a>
                </td>
                <td>
                    <%if (serviceTaskToDisplay.getResponsibleEmployee() != null) {%>
                    <a href="/ReadPersonServlet?personId=<%=serviceTaskToDisplay.getResponsibleEmployee().getId()%>&action=view-person-profile"><%=serviceTaskToDisplay.getResponsibleEmployee().getName()%>
                    </a>
                    <%
                    } else {
                        isTaskMissingEmployee = true;
                    %>
                    <span class="no-one-assigned">No one assigned</span>
                    <%}%>
                </td>
                <td><%=serviceTaskToDisplay.getAppointmentTime()%>
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
                <%}%>
            </tr>
            <%
                }
            } else {
            %>
            <h1>No services booked. <a href="/ReadVehicleServlet?action=list-vehicles">Go back to list of vehicles.</a></h1>
            <%}%>
            </tbody>
        </table>
    </div>
    <%if (isTaskMissingEmployee) {%>
    <div class="alert alert-danger">One or more service tasks is missing an assigned mechanic!</div>
    <%
        }
        if (isAdminLoggedIn) {
    %>
    <div class="alert alert-info">To toggle completion of a service task - go to that service task's profile page.</div>
    <%}%>
</div>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>