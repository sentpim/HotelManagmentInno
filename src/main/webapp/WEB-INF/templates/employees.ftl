<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Employees</h4>
                        <p class="category">for hotel 1</p>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <div class="text-center">
                            <a href="/employees/add" class="btn btn-info btn-fill btn-wd">Add new</a>
                        </div>
                        <table class="table table-striped">
                            <thead>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Post</th>
                            <th>Salary</th>
                            <th>Start date</th>
                            </thead>
                            <tbody>
                                <#list employees as employee>
                                <tr>
                                    <td>${employee.id}</td>
                                    <td>${employee.getPerson().firstName} ${employee.getPerson().lastName}</td>
                                    <td>${employee.getPost().name}</td>
                                    <td>${employee.salary}$</td>
                                    <td>${employee.startDate}</td>
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
