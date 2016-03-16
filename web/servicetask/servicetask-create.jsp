<%@ page import="nu.lansingcarworkshop.entity.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.entity.vehicle.Vehicle" %>
<%@ page import="nu.lansingcarworkshop.service.person.ReadPerson" %>
<%@ page import="nu.lansingcarworkshop.service.vehicle.ReadVehicle" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Create Service Task</title>
</head>
<body>

<%
    int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

    ReadVehicle readVehicle = new ReadVehicle();
    Vehicle vehicle = readVehicle.getVehicleById(vehicleId);
%>

<%@include file="../menu.jsp" %>

<div class="container-fluid">
    <h1><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Service</h1><br>

    <form role="form">
        <div class="form-group">
            <label for="service-time">Service Date</label>
            <input id="service-time" type="datetime-local" class="form-control" name="service-time">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="service-note" placeholder="Notes">
        </div>
        <div class="form-group">
            <label for="employee-list">Responsible Employee
                <select id="employee-list">
                    <%
                        ReadPerson readPerson = new ReadPerson();
                        List employees = readPerson.getAllEmployees();

                        for (Object employee : employees) {
                    %>
                    <option value="<%=((Employee)employee).getId()%>"><%=((Employee) employee).getName()%>
                    </option>
                    <%}%>
                </select>
            </label>
        </div>
        <button type="button" class="btn btn-success">Book Service</button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <input type="hidden" name="vehicleid" value="<%=vehicle.getId()%>">
    </form>

    <p id="feedback"></p>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="../js/servicetask-create.js"></script>
</body>
</html>