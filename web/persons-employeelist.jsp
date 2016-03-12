<%@ page import="nu.lansingcarworkshop.entity.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.service.person.GetPersons" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<h1>Staff</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Profile</th>
        <th>Update</th>
        <th>Remove</th>
    </tr>
    </thead>
    <tbody>
    <%
        GetPersons getEmployees = new GetPersons();
        List employees = getEmployees.getAllEmployees();

        for (Object person : employees) {
            if (person instanceof Employee) {
                Employee employeeToDisplay = (Employee) person;
    %>
    <tr>
        <td><%=employeeToDisplay.getName()%>
        </td>
        <td><%=employeeToDisplay.getRole().toString()%>
        </td>
        <td><a href="ReadPersonServlet?personId=<%=employeeToDisplay.getId()%>"><span
                class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="person-update.jsp?personId=<%=employeeToDisplay.getId()%>"><span
                class="glyphicon glyphicon-edit"></span></a></td>

        <td><a href="DeletePersonServlet?personId=<%=employeeToDisplay.getId()%>"><span
                class="glyphicon glyphicon-remove"></span></a></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
