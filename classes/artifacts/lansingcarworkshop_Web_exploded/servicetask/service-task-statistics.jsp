<%@ page import="nu.lansingcarworkshop.models.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.services.statistics.ReadStatistics" %>
<%@ page import="java.util.List" %>
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
    List<Customer> customersWithTheMostServiceBookings = (List<Customer>) getServletConfig().getServletContext().getAttribute("customersWithTheMostServiceBookings");
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
    <div class="row">
        <div class="col-lg-10">
            <div class="panel panel-primary">
                <div class="panel-heading">Customer(s) With the Most Service Bookings</div>
                <h1>
                    <%
                        if (customersWithTheMostServiceBookings != null && (customersWithTheMostServiceBookings.size() != 0)) {
                            for (Customer customer : customersWithTheMostServiceBookings) {
                    %>
                    <div class="panel-body">
                        <a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=view-person-profile"><%=customer.getName()%>
                        </a>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <div class="panel-body">
                        No service tasks added yet.
                    </div>
                    <%}%>
                </h1>
            </div>
        </div>
    </div>
</div>
</body>
</html>