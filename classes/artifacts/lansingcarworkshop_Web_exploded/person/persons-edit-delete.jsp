<%@ page import="nu.lansingcarworkshop.models.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.models.person.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Create/Update/Delete Persons</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container-fluid">
    <%
        List employees = (List) getServletConfig().getServletContext().getAttribute("listOfEmployees");
        List customers = (List) getServletConfig().getServletContext().getAttribute("listOfCustomers");
    %>
    <h1>Customers</h1>
    <%if (customers.size() > 0 && customers != null) {%>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Add Vehicle</th>
                <th>Profile</th>
                <th>Update</th>
                <%if (isAdminLoggedIn) {%>
                <th>Remove</th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <%
                for (Object person : customers) {
                    Customer customer = (Customer) person;
            %>
            <tr id="entry-<%=customer.getId()%>">
                <td><%=customer.getName()%>
                </td>
                <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=add-vehicle-to-person"><span class="glyphicon glyphicon-plus"></span></a></td>
                <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=view-person-profile"><span class="glyphicon glyphicon-user"></span></a></td>
                <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=update-person-profile"><span class="glyphicon glyphicon-edit"></span></a></td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <a href="#">
                <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=customer.getId()%>">
                </span>
                    </a>
                </td>
                <%}%>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <%} else {%>
    <h3>No customers added.</h3>
    <%}%>
    <%if (isAdminLoggedIn) {%>
    <div class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        Removing a customer will also remove all linked vehicles and service tasks.
    </div>
    <%}%>
    <h1>Staff</h1>
    <%if (employees.size() > 0 && employees != null) {%>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Role</th>
                <th>Profile</th>
                <th>Update</th>
                <%if (isAdminLoggedIn) {%>
                <th>Remove</th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <%
                for (Object person : employees) {
                    Employee employee = (Employee) person;
            %>
            <tr id="entry-<%=employee.getId()%>">
                <td><%=employee.getName()%>
                </td>
                <td><%=employee.getRoleFormatted()%>
                </td>
                <td><a href="/ReadPersonServlet?personId=<%=employee.getId()%>&action=view-person-profile"><span class="glyphicon glyphicon-user"></span></a></td>
                <td><a href="/ReadPersonServlet?personId=<%=employee.getId()%>&action=update-person-profile"><span class="glyphicon glyphicon-edit"></span></a></td>
                <%if (isAdminLoggedIn) {%>
                <td>
                    <a href="#">
                <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=employee.getId()%>">
                </span>
                    </a>
                </td>
                <%}%>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <%} else {%>
    <h3>No employees added.</h3>
    <%}%>
</div>
<script src="../js/person-delete.js"></script>
</body>
</html>