<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Hotel Managment System</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <link href="/css/bootstrap.min.css" rel="stylesheet"/>

    <link href="/css/animate.min.css" rel="stylesheet"/>

    <link href="/css/paper-dashboard.css" rel="stylesheet"/>

    <link href="/css/demo.css" rel="stylesheet"/>

    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="/css/themify-icons.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">

        <div class="sidebar-wrapper">

            <ul class="nav">
                <li <#if is_user_category??>class="active"</#if>>
                    <a href="/user">
                        <i class="ti-user"></i>
                        <p>User Profile</p>
                    </a>
                </li>
                <#if is_user??>
                    <li <#if is_bookings_category??>class="active"</#if>>
                        <a href="/client/bookings">
                            <i class="ti-view-list-alt"></i>
                            <p>Bookings</p>
                        </a>
                    </li>
                <#else>
                    <li <#if is_dashboard_category??>class="active"</#if>>
                        <a href="/dashboard">
                            <i class="ti-panel"></i>
                            <p>Dashboard</p>
                        </a>
                    </li>
                    <li <#if is_employees_category??>class="active"</#if>>
                        <a href="/employees">
                            <i class="ti-hummer"></i>
                            <p>Employees</p>
                        </a>
                    </li>
                    <li <#if is_bookings_category??>class="active"</#if>>
                        <a href="/bookings">
                            <i class="ti-view-list-alt"></i>
                            <p>Bookings</p>
                        </a>
                    </li>
                    <li <#if is_hotels_category??>class="active"</#if>>
                        <a href="/hotels">
                            <i class="ti-map"></i>
                            <p>Hotels</p>
                        </a>
                    </li>
                    <li <#if is_sysusers_category??>class="active"</#if>>
                        <a href="/systemusers">
                            <i class="ti-lock"></i>
                            <p>System Users</p>
                        </a>
                    </li>
                </#if>
                <li <#if is_report_category??>class="active"</#if>>
                    <a href="/report">
                        <i class="ti-pencil-alt2"></i>
                        <p>Report an error</p>
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
                        <a href="/logout">
                            <i class="ti-close"></i>
                            <p>Log Out</p>
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
                    &copy;
                    <script>document.write(new Date().getFullYear())</script>
                    ,
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

<script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>

<script src="/js/bootstrap-checkbox-radio.js"></script>

<script src="/js/chartist.min.js"></script>

<script src="/js/bootstrap-notify.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<script src="/js/paper-dashboard.js"></script>

<script src="/js/demo.js"></script>


</html>
