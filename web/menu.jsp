<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp"><span class="glyphicon glyphicon-wrench">&nbsp;</span>Lansing's Car
                Workshop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="GetPersonsServlet">Person
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="person-create.jsp">Create</a></li>
                    <li><a href="persons-edit-delete.jsp">Manage</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>