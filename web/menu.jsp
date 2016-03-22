<%
    boolean isAdminLoggedIn = (boolean) session.getAttribute("isAdminLoggedIn");
    boolean isLoginSuccessful = (boolean) session.getAttribute("isLoginSuccessful");
%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#lcw-navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><span class="glyphicon glyphicon-wrench">&nbsp;</span>Lansing's Car Workshop</a>
        </div>
        <div class="collapse navbar-collapse" id="lcw-navbar">
            <ul class="nav navbar-nav">
                <li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Person
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <%if (isAdminLoggedIn) {%>
                        <li><a href="<%=request.getContextPath()%>/person/person-create.jsp">Create</a></li>
                        <%}%>
                        <li><a href="/ReadPersonServlet?action=listpersons">List</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Vehicle
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/ReadVehicleServlet?action=listvehicles">List</a></li>
                        <%if (isAdminLoggedIn) {%>
                        <li><a href="#">Statistics</a></li>
                        <%}%>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Service
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/ReadServiceTaskServlet?action=listservicetasks">List</a></li>
                        <li><a href="/ReadServiceTaskServlet?action=listupcomingservicetasks">Upcoming Appointments</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/LoginServlet?action=logout"><%if (isAdminLoggedIn) {%>Admin: Log Out<%} else if (isLoginSuccessful) {%>Guest: Log Out<%} else {%>Log in<%}%></a></li>
            </ul>
        </div>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>