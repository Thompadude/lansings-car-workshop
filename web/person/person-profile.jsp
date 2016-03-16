<%@ page import="nu.lansingcarworkshop.entity.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.entity.person.Person" %>
<%@ page import="nu.lansingcarworkshop.entity.vehicle.Vehicle" %>
<%@ page import="nu.lansingcarworkshop.service.vehicle.ReadVehicle" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Person Profile</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    Person personToDisplay = (Person) getServletConfig().getServletContext().getAttribute("currentPerson");

    Period period = Period.between(personToDisplay.getBirthdate(), LocalDate.now());
%>

<div class="container">
    <h1>
        <span class="glyphicon glyphicon-user"></span>
        <%=personToDisplay.getName()%>
        <small><%=personToDisplay.getId()%>
            &nbsp;
            <a href="person-update.jsp?personId=<%=personToDisplay.getId()%>"><span class="glyphicon glyphicon-edit"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"><input type="hidden" value="<%=personToDisplay.getId()%>"></span></a>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Age</div>
                <div class="panel-body"><%=period.getYears()%>
                    <small>(Born: <%=personToDisplay.getBirthdate()%>)</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Address</div>
                <div class="panel-body"><%=personToDisplay.getContactInformation().getAddress()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Phone Number</div>
                <div class="panel-body"><%=personToDisplay.getContactInformation().getPhonenumber()%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Sex</div>
                <div class="panel-body"><%=personToDisplay.getSexFormatted()%>
                </div>
            </div>
        </div>
    </div>
    <%
        if (personToDisplay instanceof Customer) {
    %>
    <div class="row">
        <div class="col-lg-6">
            <h1>Vehicles Owned</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Make</th>
                    <th>Registration Plate</th>
                    <th>View Details</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ReadVehicle readVehicle = new ReadVehicle();
                    List vehicles = readVehicle.getAllCarsByCustomerId(personToDisplay);
                    //TODO add more methods for other types of vehicles in list query.
                    for (Object vehicle : vehicles) {
                %>
                <tr>
                    <td>
                        <%=((Vehicle) vehicle).getMake()%>
                    </td>
                    <td>
                        <%=((Vehicle) vehicle).getRegistrationPlate()%>
                    </td>
                    <td>
                        <a href="/ReadVehicleServlet?vehicleId=<%=((Vehicle) vehicle).getId()%>"><span
                                class="glyphicon glyphicon-info-sign"></span></a>
                    </td>
                </tr>
                <%
                    }

                %>
                </tbody>
            </table>
        </div>
    </div>
    <%
    } else {
    %>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Company Role</div>
                <div class="panel-body"><%=((Employee) personToDisplay).getRoleFormatted()%>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>
</div>
<script src="../js/person-delete.js"></script>
</body>
</html>