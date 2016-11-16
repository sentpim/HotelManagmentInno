<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

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

<div class="col-lg-6 col-md-8 col-lg-offset-3 col-md-offset-2" style="margin-top: 200px;">
    <div class="well">
        <@form.form action="/sign_up" id="form_sign_up" method="post" modelAttribute="systemUserForm" class="form-horizontal">
            <fieldset>
                <legend>Sign Up</legend>

                <div class="form-group">
                    <label for="email" class="col-lg-3 col-md-3 control-label">Email</label>
                    <div class="col-lg-9 col-md-9">
                        <@form.input path="email" type="email" placeholder="Email" class="form-control" autofocus="true" required="required"/>
                        <@form.errors path="email" element="div" class="input-has-error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-lg-3 col-md-3 control-label">Password</label>
                    <div class="col-lg-9 col-md-9">
                        <@form.input path="password" type="password" placeholder="Password"  class="form-control" required="required" />
                        <@form.errors path="password" element="div" class="input-has-error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password_retype" class="col-lg-3 col-md-3 control-label">Re-Password</label>
                    <div class="col-lg-9 col-md-9">
                        <input id="password_retype" type="password" placeholder="Password re-type"  class="form-control" required="required" />
                        <div id="password_retype_error" class="input-has-error">
                            Re-typed Password is wrong!
                        </div>
                    </div>
                </div>

                <hr>

                <div class="form-group">
                    <label for="firstName" class="col-lg-3 col-md-3 control-label">First Name</label>
                    <div class="col-lg-9 col-md-9">
                        <@form.input path="firstName" type="text" placeholder="First name" class="form-control" required="required" />
                        <@form.errors path="firstName" element="div" class="input-has-error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastName" class="col-lg-3 col-md-3 control-label">Last Name</label>
                    <div class="col-lg-9 col-md-9">
                        <@form.input path="lastName" type="text" placeholder="Last name" class="form-control" required="required" />
                        <@form.errors path="lastName" element="div" class="input-has-error"/>
                    </div>
                </div>

                <br>

                <button type="submit" class="btn btn-success">
                    Sign Up
                </button>
                &nbsp
                <a href="/sign_in">Already have account</a>
            </fieldset>
        </@form.form>
    </div>
</div>

</body>

</html>