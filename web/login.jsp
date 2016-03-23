<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; LOGIN</title>
</head>
<body>
<div class="container-fluid">
    <h1><span class="glyphicon glyphicon-wrench"></span> Lansing's Car Workshop</h1>
    <br>
    <br>

    <form class="form-inline" role="form">
        <div class="form-group">
            <label for="username">
                <input id="username" class="form-control" type="text" placeholder="User Name">
            </label>
            <label for="password">
                <input id="password" class="form-control" type="password" placeholder="Password">
            </label>
        </div>
        <button type="button">Login</button>
    </form>
    <br>
    <br>

    <div class="row">
        <div class="col-lg-5">
            <div class="alert alert-info">
                <p>admin/admin for admin login.</p>

                <p>Any user name/password for guest login.</p>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="js/login.js"></script>
</body>
</html>