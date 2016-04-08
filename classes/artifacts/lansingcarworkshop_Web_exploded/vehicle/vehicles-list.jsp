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
<div class="container-fluid">
    <h1>All Vehicles</h1>

    <div class="table-responsive">
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
            <tbody>
            <%
                for (Object vehicleToDisplay : vehicles) {
                    Vehicle vehicle = ((Vehicle) vehicleToDisplay);
            %>
            <tr id="entry-<%=vehicle.getId()%>">
                <td><%=vehicle.getMake()%>
                </td>
                <td><%=vehicle.getRegistrationPlate()%>
                </td>
                <td>
                    <a href="/ReadPersonServlet?personId=<%=vehicle.getCustomer().getId()%>&action=view-person-profile"><%=vehicle.getCustomer().getName()%>
                    </a>
                </td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <a href="/ReadVehicleServlet?vehicleId=<%=vehicle.getId()%>&action=create-service-task"><span class="glyphicon glyphicon-wrench"></span></a>
                </td>
                <%}%>
                <td>
                    <a href="/ReadVehicleServlet?vehicleId=<%=vehicle.getId()%>&action=view-vehicle"><span class="glyphicon glyphicon-info-sign"></span></a>
                </td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=vehicle.getId()%>"></span></a>
                </td>
                <%}%>
            </tr>
            <%
                }
            } else {
            %>
            </tbody>
            <h1>No vehicles added. <a href="/ReadPersonServlet?action=list-persons">Go back to list of persons.</a></h1>
            <%}%>
        </table>
    </div>
    <%if (isAdminLoggedIn) {%>
    <div class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        Removing a vehicle will also remove all linked service tasks.
    </div>
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        To add more vehicles, go to <a href="/ReadPersonServlet?action=list-persons">the list of persons</a>, navigate to the customers list and click <span class="glyphicon glyphicon-plus"></span>
    </div>
    <%}%>
</div>
<script src="../js/vehicle-delete.js"></script>
</body>
</html>