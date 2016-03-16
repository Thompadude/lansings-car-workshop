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
    <tbody>
    <%
        ReadServiceTask readServiceTask = new ReadServiceTask();
        List serviceTasks = readServiceTask.getAllServiceTasks();

        for (Object serviceTask : serviceTasks) {
    %>
    <tr id="entry-<%=((ServiceTask) serviceTask).getId()%>">
        <td><%=((ServiceTask) serviceTask).getVehicle().getMake()%>
        </td>
        <td><%=((ServiceTask) serviceTask).getVehicle().getRegistrationPlate()%>
        </td>
        <td>
            <a href="/ReadPersonServlet?personId=<%=((ServiceTask) serviceTask).getVehicle().getCustomer().getId()%>">
                <%=((ServiceTask) serviceTask).getVehicle().getCustomer().getName()%>
            </a>
        </td>
        <td>
            <a href="/ReadPersonServlet?personId=<%=((ServiceTask) serviceTask).getResponsibleEmployee().getId()%>">
                <%=((ServiceTask) serviceTask).getResponsibleEmployee().getName()%>
            </a>
        </td>
        <td>
            <%=((ServiceTask) serviceTask).getAppointmentTime()%>
        </td>
        <td>
            <a href="#"><span class="glyphicon glyphicon-edit"></span></a>
        </td>
        <td><a href="/ReadServiceTaskServlet?serviceTaskId=<%=((ServiceTask) serviceTask).getId()%>"><span
                class="glyphicon glyphicon-info-sign"></span></a></td>
        <td>
            <a href="#">
            <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=((ServiceTask) serviceTask).getId()%>">
                </span>
            </a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<script src="../js/servicetask-delete.js"></script>
</body>
</html>