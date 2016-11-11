<#macro main title="Hello!" customScripts=[] customStyles=[]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>${title}</title>

    <#--Semantic UI CDN-->
    <link rel="stylesheet" type="text/css" class="ui" href="https://cdn.jsdelivr.net/semantic-ui/2.1.8/semantic.min.css">

    <#--<link href="/css/bootstrap.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">
    <link href="/css/bootswatch.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">



    <#list customStyles as style>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </#list>
</head>
<body>

    <#include "header.ftl"/>

    <div class="content-wrap container">
        <@m_body/>
    </div>

    <footer class="container">
        <div class="row">
            <div class="col-md-10 font-12">
                <h4>Order pizza at Pizza Market</h4>
                <div>
                    There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.
                </div>
            </div>
            <div class="col-md-2 text-right font-12">
                <a href="http://vk.com/anvarzkr">Designed by Anvar Zakirov</a>
                Pizza Market © 2016
            </div>
        </div>
    </footer>

    <script src="/js/jquery.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/semantic-ui/2.1.8/semantic.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <#list customScripts as script>
        <script src="${script}"></script>
    </#list>

</body>
</html>
</#macro>