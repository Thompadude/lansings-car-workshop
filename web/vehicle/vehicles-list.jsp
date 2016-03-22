<%@ page import="nu.lansingcarworkshop.models.vehicle.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Vehicle List</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<%
    List vehicles = (List) getServletConfig().getServletContext().getAttribute("listOfVehicles");
    if (vehicles.size() > 0 && vehicles != null) {
%>
<h1>All Vehicles</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Make</th>
        <th>Registration Plate</th>
        <th>Owner</th>
        <%if (isAdminLoggedIn) {%>
        <th>Book Service</th>
        <%}%>
        <th>View Details</th>
        <%if (isAdminLoggedIn) {%>
        <th>Remove</th>
        <%}%>
    </tr>
    </thead>
    <%
        for (Object vehicleToDisplay : vehicles) {
            Vehicle vehicle = ((Vehicle) vehicleToDisplay);
    %>
    <tbody>
    <tr id="entry-<%=vehicle.getId()%>">
        <td><%=vehicle.getMake()%>
        </td>
        <td><%=vehicle.getRegistrationPlate()%>
        </td>
        <td>
            <a href="/ReadPersonServlet?personId=<%=vehicle.getCustomer().getId()%>&action=viewprofile"><%=vehicle.getCustomer().getName()%>
            </a>
        </td>
        <%if (isAdminLoggedIn) {%>
        <td>
            <a href="/ReadVehicleServlet?vehicleId=<%=vehicle.getId()%>&action=createservicetask"><span class="glyphicon glyphicon-wrench"></span></a>
        </td>
        <%}%>
        <td>
            <a href="/ReadVehicleServlet?vehicleId=<%=vehicle.getId()%>&action=viewprofile"><span class="glyphicon glyphicon-info-sign"></span></a>
        </td>
        <%if (isAdminLoggedIn) {%>
        <td>
            <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=vehicle.getId()%>"></span></a>
        </td>
        <%}%>
    </tr>
    </tbody>
    <%
        }
    } else {
    %>
    <h1>No vehicles added. <a href="/ReadPersonServlet?action=listpersons">Go back to list of persons.</a></h1>
    <%}%>
</table>
<script src="../js/vehicle-delete.js"></script>
</body>
</html>