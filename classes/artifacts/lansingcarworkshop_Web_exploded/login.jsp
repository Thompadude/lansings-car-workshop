<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>LCW &mdash; LOGIN</title>
</head>
<body>
<div class="container-fluid">

    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        admin/admin for admin login.<br>
        Any user name/password for guest login.
    </div>
    <div class="well">
        <div class="row">
            <div class="col-sm-offset-2 col-sm-6">
                <h1>
                    <span class="glyphicon glyphicon-wrench"></span> Lansing's Car Workshop
                </h1>
            </div>
        </div>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="control-label col-sm-2" for="username">User Name</label>

                <div class="col-sm-6">
                    <input id="username" class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password</label>

                <div class="col-sm-6">
                    <input id="password" class="form-control" type="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="button">Login</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/login.js"></script>
</body>
</html>