<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	 <title>Hotel Managment System</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/css/bootstrap.min.css" rel="stylesheet" />

    <link href="/css/animate.min.css" rel="stylesheet"/>

    <link href="/css/paper-dashboard.css" rel="stylesheet"/>

    <link href="/css/demo.css" rel="stylesheet" />

    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="/css/themify-icons.css" rel="stylesheet">

</head>
<body>


<div class="wrapper">
     <div class="sidebar" data-background-color="white" data-active-color="danger">

        <div class="sidebar-wrapper">

            <ul class="nav">
                <li>
                    <a href="user.ftl">
                        <i class="ti-user"></i>
                        <p>User Profile</p>
                    </a>
                </li>
                <li>
                    <a href="dashboard.ftl">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="employees.ftl">
                        <i class="ti-view-list-alt"></i>
                        <p>Employees</p>
                    </a>
                </li>
                <li>
                    <a href="bookings.ftl">
                        <i class="ti-view-list-alt"></i>
                        <p>Booking</p>
                    </a>
                </li>
                <li class="active">
                    <a href="hotels.ftl">
                        <i class="ti-map"></i>
                        <p>Hotels</p>
                    </a>
                </li>
                <li>
                    <a href="report.ftl">
                        <i class="ti-pencil-alt2"></i>
                        <p>Report an error</p>
                    </a>
                </li>
                <li>
                    <a href="sysusers.ftl">
                        <i class="ti-bell"></i>
                        <p>Notifications</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>    
       <div class="main-panel">
    <nav class="navbar navbar-default">
            <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-panel"></i>
                                <p>Stats</p>
                            </a>
                        </li>
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="ti-bell"></i>
                                    <p class="notification">5</p>
                                    <p>Notifications</p>
                                    <b class="caret"></b>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Notification 1</a></li>
                                <li><a href="#">Notification 2</a></li>
                                <li><a href="#">Notification 3</a></li>
                                <li><a href="#">Notification 4</a></li>
                                <li><a href="#">Another notification</a></li>
                              </ul>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ti-settings"></i>
                                <p>Settings</p>
                            </a>
                        </li>
                    </ul>

                </div>
        </nav>  
       <div class="content">
        <@m_body/>
    </div>

<footer class="footer">
            <div class="container-fluid">
                <div class="copyright pull-right">
                    &copy; <script>document.write(new Date().getFullYear())</script>, 
                    Created by 
                    <a href="https://vk.com/anvarzkr">
                        Anvar Zakirov 
                    </a>
                    and
                    <a href="https://vk.com/sentp1m">
                        Roman Tatarskikh
                    </a>
                </div>
            </div>
        </footer>
</div>
</div>

</body>

    <!--   Core JS Files   -->
    <script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="/js/demo.js"></script>


</html>
