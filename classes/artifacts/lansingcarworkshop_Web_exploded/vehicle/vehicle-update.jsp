<%@ page import="nu.lansingcarworkshop.models.vehicle.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Update Vehicle</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container-fluid">
    <%
        Vehicle vehicleToUpdate = (Vehicle) getServletConfig().getServletContext().getAttribute("currentVehicle");

        if (isAdminLoggedIn) {
            if (!(vehicleToUpdate == null)) {
    %>
    <form id="updateform" role="form" action="/UpdateVehicleServlet" method="POST">
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-registrationplate" value="<%=vehicleToUpdate.getRegistrationPlate()%>">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-make" value="<%=vehicleToUpdate.getMake()%>">
        </div>
        <div class="form-group">
            <label for="date">Manufacturing Date</label>
            <input id="date" type="date" class="form-control" name="vehicle-modelyear" value="<%=vehicleToUpdate.getModelYear()%>">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-fueltype" value="<%=vehicleToUpdate.getFuel()%>">
        </div>
        <button type="submit" class="btn btn-success" value="submit">Update</button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <a href="../index.jsp">
            <button type="button" class="btn btn-danger">Cancel</button>
        </a>
        <input type="hidden" name="vehicleid" value="<%=vehicleToUpdate.getId()%>">
    </form>
    <%} else {%>
    <h1>No vehicle choosen. <a href="/ReadVehicleServlet?action=list-vehicles">Go back</a> to list of vehicles</h1>
    <%
        }
    } else {
    %><h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1><%}%>
</div>
</body>
</html>