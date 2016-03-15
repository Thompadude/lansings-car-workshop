<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><span class="glyphicon glyphicon-wrench">&nbsp;</span>Lansing's Car
                Workshop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="">Person
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<%=request.getContextPath()%>/person/person-create.jsp">Create</a></li>
                    <li><a href="<%=request.getContextPath()%>/person/persons-edit-delete.jsp">List</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="">Vehicle
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<%=request.getContextPath()%>/vehicle/vehicles-list.jsp">List</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>