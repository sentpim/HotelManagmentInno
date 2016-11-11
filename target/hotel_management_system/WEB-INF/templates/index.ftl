<#include "main-template.ftl"/>
<#macro m_body>

<header class="jumbotron hero-spacer text-center hero-pizza">
    <h1>Pizza Market</h1>
    <p>Get the most delicious pizza here!</p>
    <p>
        <#if auth??>
            <a href="/products" class="btn btn-primary btn-lg">Get Pizza</a>
        <#else >
            <a href="/sign_in" class="btn btn-primary btn-lg">Log In</a>
            <a href="/sign_up" class="btn btn-success btn-lg">Sign Up</a>
        </#if>

    </p>
</header>

</#macro>
<@main title="Home Page"/>