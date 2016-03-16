<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; Create/Update/Delete Persons</title>
</head>
<body>

<%@include file="../menu.jsp" %>

<div id="person-list">
    <%@include file="persons-customerslist.jsp" %>
    <%@include file="persons-employeelist.jsp" %>
</div>

<script src="../js/person-delete.js"></script>
</body>
</html>