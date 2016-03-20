<%@ page import="nu.lansingcarworkshop.models.person.Customer" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<%
    boolean isAdminLoggedInOnCustomersList = (boolean) session.getAttribute("isAdminLoggedIn");

    List customers = (List) getServletConfig().getServletContext().getAttribute("listOfCustomers");
%>
<h1>Customers</h1>
    <%if (customers.size() > 0 && customers != null) {%>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Add Vehicle</th>
        <th>Profile</th>
        <th>Update</th>
        <%if (isAdminLoggedInOnCustomersList) {%><th>Remove</th><%}%>
    </tr>
    </thead>
    <tbody>
    <%
            for (Object person : customers) {
                Customer customer = (Customer) person;
    %>
    <tr id="entry-<%=customer.getId()%>">
        <td><%=customer.getName()%></td>
        <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=addvehicle"><span class="glyphicon glyphicon-plus"></span></a></td>
        <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=viewprofile"><span class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&action=updateprofile"><span class="glyphicon glyphicon-edit"></span></a></td>
        <%if (isAdminLoggedInOnCustomersList){%>
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
<%} else {%>
<h3>No customers added.</h3>
<%}%>