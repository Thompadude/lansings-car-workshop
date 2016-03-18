<%@ page import="nu.lansingcarworkshop.entity.person.Customer" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<h1>Customers</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Add Vehicle</th>
        <th>Profile</th>
        <th>Update</th>
        <th>Remove</th>
    </tr>
    </thead>
    <tbody>
    <%
        List customers = (List) getServletConfig().getServletContext().getAttribute("listOfCustomers");

        for (Object person : customers) {
            Customer customer = (Customer) person;
    %>
    <tr id="entry-<%=customer.getId()%>">
        <td><%=customer.getName()%>
        <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>&hasAddVehicleBeenRequested=true"><span class="glyphicon glyphicon-plus"></span></a></td>
        <td><a href="/ReadPersonServlet?personId=<%=customer.getId()%>"><span class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="person-update.jsp?personId=<%=customer.getId()%>"><span class="glyphicon glyphicon-edit"></span></a></td>
        <td>
            <a href="#">
                <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=customer.getId()%>">
                </span>
            </a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>