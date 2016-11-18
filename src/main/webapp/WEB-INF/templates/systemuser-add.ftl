<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <div class="content">
                        <@form.form action="/systemusers/add" id="form_systemusers_add" method="post" modelAttribute="systemUserForm">
                            <@form.input path="id" type="hidden" class="form-control border-input hidden" placeholder="Id" value="" />
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Customer</label>
                                        <@form.select path="personId" class="form-control border-input">
                                            <#list persons as person>
                                                <option value="${person.id?c}" <#if person.id == systemUserForm.getPersonId()>selected</#if>>${person.id}
                                                    : ${person.firstName} ${person.lastName}</option>
                                            </#list>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <@form.input path="email" type="text" class="form-control border-input" placeholder="Email" value="" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Password</label>
                                        <@form.input path="password" type="password" class="form-control border-input" placeholder="Password" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Role</label>
                                        <@form.select path="roleId" class="form-control border-input">
                                            <option value="0" <#if systemUserForm.getRoleId() == 0>selected</#if>>Superuser</option>
                                            <option value="1" <#if systemUserForm.getRoleId() == 1>selected</#if>>Administrator</option>
                                            <option value="2" <#if systemUserForm.getRoleId() == 2>selected</#if>>Reception</option>
                                            <option value="3" <#if systemUserForm.getRoleId() == 3>selected</#if>>User</option>
                                        </@form.select>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-info btn-fill btn-wd">Add new</button>
                            </div>
                            <div class="clearfix"></div>
                        </@form.form>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>





</#macro>
