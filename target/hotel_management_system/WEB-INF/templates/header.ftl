<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<@security.authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
    <@security.authentication property="principal.email" var="auth"/>
    <@security.authentication property="principal.role" var="role"/>
</@security.authorize>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Pizza Market</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

                <li>
                    <a href="/products">Products</a>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
            <#if auth??>
                <li>
                    <a href="/cart">Cart</a>
                </li>
                <li>
                    <a href="/settings">Settings</a>
                </li>
                <li>
                    <a href="/orders">My Orders</a>
                </li>
                <li>
                    <a href="/logout">Log Out</a>
                </li>
                <#if role == 'ROLE_ADMIN'>
                    <li>
                        <a href="/admin/">Admin Panel</a>
                    </li>
                </#if>
            <#else>
                <li>
                    <a href="/sign_in">Log In</a>
                </li>
                <li>
                    <a href="/sign_up">Sign Up</a>
                </li>
            </#if>
            </ul>
        </div>

        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
