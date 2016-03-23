<%@ page import="nu.lansingcarworkshop.models.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.models.vehicle.Vehicle" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Create Service Task</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container-fluid">
    <%
        Vehicle vehicle = (Vehicle) getServletConfig().getServletContext().getAttribute("currentVehicle");
        List employees = (List) getServletConfig().getServletContext().getAttribute("listOfEmployees");

        if (isAdminLoggedIn) {
    %>
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
            <label for="employee-list">Responsible Employee</label>
                <select class="form-control" id="employee-list">
                    <%for (Object employee : employees) {%>
                    <option value="<%=((Employee)employee).getId()%>"><%=((Employee) employee).getName()%>
                    </option>
                    <%}%>
                </select>
        </div>
        <button type="button" class="btn btn-success">Book Service</button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <input type="hidden" name="vehicle-id" value="<%=vehicle.getId()%>">
    </form>

    <p id="feedback"></p>
    <%} else {%>
    <h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1>
    <%}%>
</div>
<script src="../js/service-task-create.js"></script>
</body>
</html>