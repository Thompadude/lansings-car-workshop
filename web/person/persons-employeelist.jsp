<%@ page import="nu.lansingcarworkshop.models.person.Employee" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<%
    boolean isAdminLoggedInOnEmployeeList = (boolean) session.getAttribute("isAdminLoggedIn");

    List employees = (List) getServletConfig().getServletContext().getAttribute("listOfEmployees");
%>
<h1>Staff</h1>
    <%if (employees.size() > 0 && employees != null) {%>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Profile</th>
        <th>Update</th>
        <%if (isAdminLoggedInOnEmployeeList) {%><th>Remove</th><%}%>
    </tr>
    </thead>
    <tbody>
    <%
            for (Object person : employees) {
                Employee employee = (Employee) person;
    %>
    <tr id="entry-<%=employee.getId()%>">
        <td><%=employee.getName()%></td>
        <td><%=employee.getRoleFormatted()%></td>
        <td><a href="/ReadPersonServlet?personId=<%=employee.getId()%>&action=viewprofile"><span class="glyphicon glyphicon-user"></span></a></td>
        <td><a href="/ReadPersonServlet?personId=<%=employee.getId()%>&action=updateprofile"><span class="glyphicon glyphicon-edit"></span></a></td>
        <%if (isAdminLoggedInOnEmployeeList) {%>
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
<%} else {%>
<h3>No employees added.</h3>
<%}%>