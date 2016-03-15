<%@ page import="nu.lansingcarworkshop.entity.vehicle.Vehicle" %>
<%@ page import="nu.lansingcarworkshop.service.vehicle.ReadVehicle" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Car Workshop &mdash; Vehicle Profile</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<h1>All Vehicles</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Make</th>
        <th>Registration Plate</th>
        <th>Owner</th>
        <th>View Vehicle Details</th>
    </tr>
    </thead>
    <tbody>
    <%
        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllVehicles();

        for (Object vehicle : vehicles) {
    %>
    <tr>
        <td><%=((Vehicle) vehicle).getMake()%>
        </td>
        <td><%=((Vehicle) vehicle).getRegistrationPlate()%>
        </td>
        <td>
            <a href="/ReadPersonServlet?personId=<%=((Vehicle) vehicle).getCustomer().getId()%>"><%=((Vehicle) vehicle).getCustomer().getName()%>
            </a>
        </td>
        <td>
            <a href="/ReadVehicleServlet?vehicleId=<%=((Vehicle) vehicle).getId()%>"><span
                    class="glyphicon glyphicon-info-sign"></span></a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>