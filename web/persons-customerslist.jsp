<%@ page import="nu.lansingcarworkshop.entity.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.service.person.GetPersons" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<h1>Customers</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Manage Vehicles</th>
        <th>Profile</th>
        <th>Update</th>
        <th>Remove</th>
    </tr>
    </thead>
    <tbody>
    <%
        GetPersons getCustomers = new GetPersons();
        List customers = getCustomers.getAllCustomers();

        for (Object person : customers) {
            if (person instanceof Customer) {
                Customer customerToDisplay = (Customer) person;
    %>
    <tr>
        <td><%=customerToDisplay.getName()%>
        <td><a href="person-addvehicle.jsp?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-list"></span></a></td>
        <td><a href="ReadPersonServlet?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="person-update.jsp?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-edit"></span></a></td>
        <td><a href="DeletePersonServlet?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-remove"></span></a></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>