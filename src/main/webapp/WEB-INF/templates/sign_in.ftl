<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<#include "main-template.ftl"/>
<#macro m_body>

<div class="col-lg-6 col-md-8 col-lg-offset-3 col-md-offset-2">
    <div class="well">
        <form action="/sign_in" id="form_sign_up" method="post" class="form-horizontal">
            <fieldset>
                <legend>Sign In</legend>

                <#if error??>
                    <div class="form-group text-center input-has-error">
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
            </fieldset>
        </form>
    </div>
</div>

</#macro>
<@main title="Sign In"/>