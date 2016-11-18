<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">System Users</h4>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <div class="text-center">
                            <a href="/systemusers/add" class="btn btn-info btn-fill btn-wd">Add new</a>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <th>ID</th>
                                <th>Email</th>
                                <th>Name</th>
                                <th>Role</th>
                            </thead>
                            <tbody>
                                <#list systemUsers as systemUser>
                                <tr>
                                    <td>${systemUser.id}</td>
                                    <td>${systemUser.email}</td>
                                    <td>${systemUser.getPerson().firstName} ${systemUser.getPerson().lastName}</td>
                                    <td>${systemUser.role}$</td>
                                    <td>
                                        <a href="/systemusers/${systemUser.id?c}/delete"> Delete </a>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>


        </div>
    </div>
</div>





</#macro>
