<%@ page import="nu.lansingcarworkshop.entity.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Person" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Role" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Sex" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Update Person</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    boolean isAdminLoggedIn = (boolean) session.getAttribute("isAdminLoggedIn");

    Person personToUpdate = (Person) getServletConfig().getServletContext().getAttribute("currentPerson");

    if (isAdminLoggedIn) {
        if (!(personToUpdate == null)) {
%>

<div class="container-fluid">
    <form role="form" action="/UpdatePersonServlet" method="POST">
        <div class="form-group">
            <input type="text" class="form-control" name="person-name" value="<%=personToUpdate.getName()%>" placeholder="Name">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="person-address"
                   value="<%=personToUpdate.getContactInformation().getAddress()%>" placeholder="Address">
        </div>
        <div class="form-group">
            <input type="tel" class="form-control" name="person-phone"
                   value="<%=personToUpdate.getContactInformation().getPhonenumber()%>" placeholder="Phone">
        </div>
        <div class="form-group">
            <label for="date">Birthday</label>
            <input id="date" type="date" class="form-control" name="person-birthday"
                   value="<%=personToUpdate.getBirthdate()%>">
        </div>
        <div class="form-group">
            <label><input type="radio" name="person-sex" value="male"<%if (personToUpdate.getSex().equals(Sex.MALE)) {%>
                          checked="checked"<%}%>> Male</label>
            <label><input type="radio" name="person-sex"
                          value="female"<%if (personToUpdate.getSex().equals(Sex.FEMALE)) {%> checked="checked"<%}%>>
                Female</label>
        </div>
        <%
            if (personToUpdate instanceof Employee) {
        %>
        <div class="form-group">
            <label><input type="radio" name="person-role"
                          value="technician"<%if(((Employee)personToUpdate).getRole() == Role.TECHNICIAN) {%>
                          checked="checked"<%}%>> Technician</label>
            <label><input type="radio" name="person-role"
                          value="specialist"<%if (((Employee)personToUpdate).getRole() == Role.SPECIALIST) {%>
                          checked="checked"<%}%>> Specialist</label>
        </div>
        <%}%>
        <button type="submit" class="btn btn-success" value="submit">Update</button>
        <button type="reset" class="btn btn-danger">Reset</button>
        <a href="persons-edit-delete.jsp">
            <button type="button" class="btn btn-danger">Cancel</button>
        </a>
        <input type="hidden" name="personid" value="<%=personToUpdate.getId()%>">
    </form>
</div>
<%} else {%>
<h1>Go to <a href="persons-edit-delete.jsp">person list</a> and choose person to update.</h1>
<%}} else {%><h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1><%}%>
</body>
</html>