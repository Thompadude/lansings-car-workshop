<%@ page import="nu.lansingcarworkshop.models.person.Employee" %>
<%@ page import="nu.lansingcarworkshop.models.servicetask.ServiceTask" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Update Service Task</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container-fluid">
    <%
        ServiceTask serviceTaskToUpdate = (ServiceTask) getServletConfig().getServletContext().getAttribute("currentServiceTask");
        List employees = (List) getServletConfig().getServletContext().getAttribute("listOfEmployees");

        if (isAdminLoggedIn) {
            if (!(serviceTaskToUpdate == null)) {
    %>
    <form id="updateform" role="form" action="/UpdateServiceTaskServlet" method="POST">
        <form role="form">
            <div class="form-group">
                <label for="service-time">Service Date</label>
                <input id="service-time" type="datetime-local" class="form-control" name="service-time" value="<%=serviceTaskToUpdate.getTime()%>">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="service-note" placeholder="Notes" value="<%=serviceTaskToUpdate.getNote()%>">
            </div>
            <div class="form-group">
                <label for="employee-list">Responsible Employee
                    <select id="employee-list" name="employee-list">
                        <%for (Object employee : employees) {%>
                        <option value="<%=((Employee)employee).getId()%>"><%=((Employee) employee).getName()%>
                        </option>
                        <%}%>
                    </select>
                </label>
            </div>
            <button type="submit" class="btn btn-success">Update Service</button>
            <button type="reset" class="btn btn-danger">Reset</button>
            <input type="hidden" name="servicetaskid" value="<%=serviceTaskToUpdate.getId()%>">
        </form>
    </form>
    <%} else {%>
    <h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1>
    <%
        }
    } else {
    %><h1>Access denied. <a href="../login.jsp">Log in</a> as admin to gain access.</h1><%}%>
</div>
<script src="../js/updateform.js"></script>
</body>
</html>