<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "main-template.ftl"/>
<#macro m_body>

<div class="col-lg-6 col-md-8 col-lg-offset-3 col-md-offset-2">
    <div class="well">
        <@form.form action="/sign_up" id="form_sign_up" method="post" modelAttribute="userForm" class="form-horizontal">
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

</#macro>
<@main title="Sign Up" customScripts=["/js/sign_up.js"]/>