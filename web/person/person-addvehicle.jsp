<%@ page import="nu.lansingcarworkshop.entity.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.service.person.ReadPerson" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Add Vehicle</title>
</head>
<body>

<%
    int personId = Integer.parseInt(request.getParameter("personId"));
    ReadPerson readPersonById = new ReadPerson();
    Customer customerToAddVehicleTo = (Customer) readPersonById.getPersonById(personId);
%>

<%@include file="../menu.jsp" %>

<div class="container-fluid">
    <h1><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Vehicle</h1><br>

    <form role="form">
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-registrationplate" placeholder="Registration Number">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-make" placeholder="Vehicle Make">
        </div>
        <div class="form-group">
            <label for="date">Manufacturing Date</label>
            <input id="date" type="date" class="form-control" name="vehicle-modelyear" placeholder="Vehicle Model Year">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-fueltype" placeholder="Vehicle Fuel Type">
        </div>
        <button type="button" class="btn btn-success">Add Vehicle to <%=customerToAddVehicleTo.getName()%>
        </button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <input type="hidden" name="customerid" value="<%=customerToAddVehicleTo.getId()%>">
    </form>

    <p id="feedback"></p>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="../js/person-addvehicle.js"></script>
</body>
</html>
