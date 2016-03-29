<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/stylesheet.css">
    <title>LCW &mdash; Service List</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<%
    Long amountOfCompletedServiceTasks = (Long) getServletConfig().getServletContext().getAttribute("amountOfCompletedServiceTasks");
    int numberOfUniqueCustomersWithServiceBookings = (int) getServletConfig().getServletContext().getAttribute("numberOfUniqueCustomersWithServiceBookings");
%>
<div class="container">
    <div class="row">
        <div class="col-lg-5">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Amount of Completed Service Tasks
                </div>
                <div class="panel-body">
                    <h1><%=amountOfCompletedServiceTasks%>
                    </h1>
                </div>
            </div>
        </div>
        <div class="col-lg-5">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Amount of Unique Customers With Service Bookings
                </div>
                <div class="panel-body">
                    <h1><%=numberOfUniqueCustomersWithServiceBookings%>
                    </h1>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>