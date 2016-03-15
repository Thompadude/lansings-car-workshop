<%@ page import="nu.lansingcarworkshop.entity.person.Customer" %>
<%@ page import="nu.lansingcarworkshop.service.person.ReadPerson" %>
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
        ReadPerson readPerson = new ReadPerson();
        List customers = readPerson.getAllCustomers();

        for (Object person : customers) {
            if (person instanceof Customer) {
                Customer customerToDisplay = (Customer) person;
    %>
    <tr id="entry-<%=customerToDisplay.getId()%>">
        <td><%=customerToDisplay.getName()%>
        <td><a href="person-addvehicle.jsp?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-plus"></span></a></td>
        <td><a href="/ReadPersonServlet?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="person-update.jsp?personId=<%=customerToDisplay.getId()%>"><span
                class="glyphicon glyphicon-edit"></span></a></td>
        <td>
            <a href="#">
                <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=customerToDisplay.getId()%>">
                </span>
            </a>
        </td>
    </tr>

    <%
            }
        }
    %>
    </tbody>
</table>