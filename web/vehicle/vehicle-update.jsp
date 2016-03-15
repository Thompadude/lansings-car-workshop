<%@ page import="nu.lansingcarworkshop.entity.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Person" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Role" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Sex" %>
<%@ page import="nu.lansingcarworkshop.service.person.ReadPerson" %>
<%@ page import="nu.lansingcarworkshop.service.vehicle.ReadVehicle" %>
<%@ page import="nu.lansingcarworkshop.entity.vehicle.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Car Workshop &mdash; Update Vehicle</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    String vehicleIdParameter = request.getParameter("vehicleId");
    int vehicleId = 0;

    if (!(vehicleIdParameter == null)) {
        vehicleId = Integer.parseInt(vehicleIdParameter);
    }

    ReadVehicle readVehicle = new ReadVehicle();
    Vehicle vehicleToUpdate = readVehicle.getVehicleById(vehicleId);

    if (!(vehicleToUpdate == null)) {
%>

<div class="container-fluid">
    <form role="form" action="/UpdateVehicleServlet" method="POST">
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-registrationplate" value="<%=vehicleToUpdate.getRegistrationPlate()%>">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-make"
                   value="<%=vehicleToUpdate.getMake()%>">
        </div>
        <div class="form-group">
            <input type="date" class="form-control" name="vehicle-modelyear"
                   value="<%=vehicleToUpdate.getModelYear()%>">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-fueltype"
                   value="<%=vehicleToUpdate.getFuel()%>">
        </div>
        <button type="submit" class="btn btn-success" value="submit">Update</button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <a href="../index.jsp">
            <button type="button" class="btn btn-danger">Cancel</button>
        </a>
        <input type="hidden" name="vehicleid" value="<%=vehicleToUpdate.getId()%>">
    </form>
</div>
<%} else {%>
<h1>Nothing to see here. Go to <a href="../index.jsp">home</a>.</h1>
<%}%>
</body>
</html>