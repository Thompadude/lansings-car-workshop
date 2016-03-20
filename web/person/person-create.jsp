<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Create Person</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    boolean isAdminLoggedIn = (boolean) session.getAttribute("isAdminLoggedIn");
    if (isAdminLoggedIn) {
%>
<div class="container-fluid">
    <h1><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Person</h1><br>

    <form role="form">
        <div class="form-group">
            <input type="text" class="form-control" name="person-name" placeholder="Name">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="person-address" placeholder="Address">
        </div>
        <div class="form-group">
            <input id="tel" type="tel" class="form-control" name="person-phone" placeholder="Phone">
        </div>
        <div class="form-group">
            <label for="date">Birthday</label>
            <input id='date' type="date" class="form-control" name="person-birthday">
        </div>
        <div class="form-group">
            <label><input type="radio" name="person-sex" value="male" checked="checked"> Male</label>
            <label><input type="radio" name="person-sex" value="female"> Female</label>
        </div>
        <div class="form-group">
            <label><input type="radio" name="person-role" value="customer" checked="checked"> Customer</label>
            <label><input type="radio" name="person-role" value="technician"> Technician</label>
            <label><input type="radio" name="person-role" value="specialist"> Specialist</label>
        </div>
        <button type="button" class="btn btn-success">Add</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form>

    <p id="feedback"></p>
<%} else {%><h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1><%}%>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="../js/person-create.js"></script>
</body>
</html>