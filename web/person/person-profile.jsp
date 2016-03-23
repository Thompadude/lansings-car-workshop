<%@ page import="nu.lansingcarworkshop.models.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.models.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.models.person.Person" %>
<%@ page import="nu.lansingcarworkshop.models.vehicle.Vehicle" %>
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
    Person currentPerson = (Person) getServletConfig().getServletContext().getAttribute("currentPerson");
    List currentPersonsVehicles = (List) getServletConfig().getServletContext().getAttribute("currentPersonsVehicles");

    Period period = Period.between(currentPerson.getBirthdate(), LocalDate.now());
%>
<div class="container">
    <h1>
        <span class="glyphicon glyphicon-user"></span>
        <%=currentPerson.getName()%>
        <small><%=currentPerson.getId()%>
            <%if (isAdminLoggedIn) {%>
            &nbsp;
            <a href="/ReadPersonServlet?personId=<%=currentPerson.getId()%>&action=update-person-profile">
                <span class="glyphicon glyphicon-edit"></span>
            </a>
            <a href="#"><span class="glyphicon glyphicon-remove">
                <input type="hidden" value="<%=currentPerson.getId()%>"></span>
            </a>
            <%}%>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Age</div>
                <div class="panel-body"><%=period.getYears()%>
                    <small>(Born: <%=currentPerson.getBirthdate()%>)</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Address</div>
                <div class="panel-body"><%=currentPerson.getContactInformation().getAddress()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Phone Number</div>
                <div class="panel-body"><%=currentPerson.getContactInformation().getPhonenumber()%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Sex</div>
                <div class="panel-body"><%=currentPerson.getSexFormatted()%>
                </div>
            </div>
        </div>
    </div>
    <%if (currentPerson instanceof Customer) {%>
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
                    for (Object vehicle : currentPersonsVehicles) {
                        Vehicle vehicleToDisplay = (Vehicle) vehicle;
                %>
                <tr>
                    <td>
                        <%=vehicleToDisplay.getMake()%>
                    </td>
                    <td>
                        <%=vehicleToDisplay.getRegistrationPlate()%>
                    </td>
                    <td>
                        <a href="/ReadVehicleServlet?vehicleId=<%=vehicleToDisplay.getId()%>&action=view-vehicle">
                            <span class="glyphicon glyphicon-info-sign"></span>
                        </a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <%
    } else {
        Employee employee = (Employee) currentPerson;
    %>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Company Role</div>
                <div class="panel-body"><%=employee.getRoleFormatted()%>
                </div>
            </div>
        </div>
    </div>
    <%}%>
</div>
<script src="../js/person-delete.js"></script>
</body>
</html>