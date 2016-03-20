<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; LOGIN</title>
</head>
<body>
<div class="container">
    <h1><span class="glyphicon glyphicon-wrench"></span> Lansing's Car Workshop Login</h1>
    <br>
    <br>
    <form role="form">
        <div class="form-group">
            <label for="username">
                <input id="username" class="form-control" type="text" placeholder="User">
            </label>
            <label for="password">
                <input id="password" class="form-control" type="password" placeholder="Password">
            </label>
        </div>
        <button type="button">Login</button>
    </form>
    <br>
    <br>

    <p>admin/admin for admin login.</p>

    <p>Any user name/password for guest login.</p>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="js/login.js"></script>
</body>
</html>