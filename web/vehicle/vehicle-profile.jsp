<%@ page import="nu.lansingcarworkshop.entity.vehicle.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Car Workshop &mdash; Vehicle Profile</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<%
    Vehicle vehicleToDisplay = (Vehicle) getServletConfig().getServletContext().getAttribute("currentVehicle");
%>

<div class="container">
    <h1>
        <%=vehicleToDisplay.getMake()%>
        <small><%=vehicleToDisplay.getId()%>
            &nbsp;<a href="#"><span
                    class="glyphicon glyphicon-edit"></span></a>
            <a href="#"><span class="glyphicon glyphicon-remove"></span></a>
        </small>
    </h1>
    <br>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">Owner</div>
                <div class="panel-body"><a
                        href="/ReadPersonServlet?personId=<%=vehicleToDisplay.getCustomer().getId()%>"><%=vehicleToDisplay.getCustomer().getName()%>
                </a></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Registration Plate</div>
                <div class="panel-body"><%=vehicleToDisplay.getRegistrationPlate()%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Make</div>
                <div class="panel-body"><%=vehicleToDisplay.getMake()%>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Model Year</div>
                <div class="panel-body"><%=vehicleToDisplay.getModelYear().getYear()%>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Fuel Type</div>
                <div class="panel-body"><%=vehicleToDisplay.getFuel()%>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="vehicleId" value="<%=vehicleToDisplay.getId()%>">
<script src="../js/vehicle-delete.js"></script>
</body>
</html>
