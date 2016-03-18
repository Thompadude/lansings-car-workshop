<%@ page import="nu.lansingcarworkshop.entity.person.Employee" %>
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
        List employees = (List) getServletConfig().getServletContext().getAttribute("listOfEmployees");

        for (Object person : employees) {
            Employee employee = (Employee) person;
    %>
    <tr id="entry-<%=employee.getId()%>">
        <td><%=employee.getName()%>
        </td>
        <td><%=employee.getRoleFormatted()%>
        </td>
        <td><a href="/ReadPersonServlet?personId=<%=employee.getId()%>"><span class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="person-update.jsp?personId=<%=employee.getId()%>"><span class="glyphicon glyphicon-edit"></span></a></td>
        <td>
            <a href="#">
                <span class="glyphicon glyphicon-remove">
                    <input type="hidden" value="<%=employee.getId()%>">
                </span>
            </a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>