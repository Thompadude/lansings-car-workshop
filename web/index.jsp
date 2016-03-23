<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>LCW &mdash; Welcome</title>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container-fluid">
    <% if (!isAdminLoggedIn) {%>
    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        You are logged in as a guest. You only have read rights.
    </div>
    <%}%>
    <img class="img-responsive" src="images/banner.jpg" alt="Banner">
</div>
<script src="js/index.js"></script>
</body>
</html>
