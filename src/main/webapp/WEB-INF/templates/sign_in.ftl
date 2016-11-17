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
            <form action="/sign_in" id="form_sign_up" method="post" class="form-horizontal">
                <fieldset>
                    <legend>Sign In</legend>

                <#if error??>
                    <div class="form-group text-center input-has-error text-danger">
                        <div>Wrong email or password!</div>
                    </div>
                </#if>

                    <div class="form-group">
                        <label for="email" class="col-lg-3 col-md-3 control-label">Email</label>
                        <div class="col-lg-9 col-md-9">
                            <input type="email" name="email" placeholder="Email" class="form-control" autofocus="true" required="required" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-lg-3 col-md-3 control-label">Password</label>
                        <div class="col-lg-9 col-md-9">
                            <input type="password" name="password" placeholder="Password"  class="form-control" required="required" />
                        </div>
                    </div>

                    <button type="submit" class="btn btn-success">
                        Sign In
                    </button>
                    &nbsp
                    <a href="/sign_up">Don't have account</a>
                    &nbsp
                    |
                    &nbsp
                    <a href="/client/sign_ip">I'am a client</a>
                    
                </fieldset>
            </form>
        </div>
    </div>

</body>

</html>