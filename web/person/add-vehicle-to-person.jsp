<%@ page import="nu.lansingcarworkshop.models.person.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Add Vehicle</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container-fluid">
    <%
        if (isAdminLoggedIn) {
            Customer customer = (Customer) getServletConfig().getServletContext().getAttribute("currentPerson");
    %>
    <h1>
        <span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Add Vehicle to <%=customer.getName()%>
    </h1>
    <br>

    <form role="form">
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-registration-plate" placeholder="Registration Number">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-make" placeholder="Vehicle Make">
        </div>
        <div class="form-group">
            <label for="date">Manufacturing Date</label>
            <input id="date" type="date" class="form-control" name="vehicle-modely-ear" placeholder="Vehicle Model Year">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="vehicle-fuel-type" placeholder="Vehicle Fuel Type">
        </div>
        <button type="button" class="btn btn-success">Add Vehicle to <%=customer.getName()%>
        </button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <input type="hidden" name="customer-id" value="<%=customer.getId()%>">
    </form>
    <p id="feedback"></p>
    <%} else {%><h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1><%}%>
</div>
<script src="../js/add-vehicle-to-person.js"></script>
</body>
</html>