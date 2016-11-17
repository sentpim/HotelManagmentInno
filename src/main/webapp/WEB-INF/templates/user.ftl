<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "template.ftl"/>

<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-4 col-md-5">
                <div class="card card-user">
                    <div class="image">
                        <img src="/img/background.jpg" alt="..."/>
                    </div>
                    <div class="content">
                        <div class="author">
                            <img class="avatar border-white" src="/img/faces/face-2.jpg" alt="..."/>
                            <h4 class="title">${user.firstName} ${user.lastName}<br/>
                                <a href="#">
                                    <small>${user.email}</small>
                                </a>
                            </h4>
                        </div>
                        <#if user_employee??>
                            <p class="description text-center">
                            ${user_employee.getPost().name}
                                <br>
                            </p>
                        </#if>
                    </div>
                    <hr>
                    <#if user_employee??>
                        <div class="text-center">
                            <div class="row">
                                <div class="col-md-3 col-md-offset-1">
                                    <h5>0<br/>
                                        <small>Tips</small>
                                    </h5>
                                </div>
                                <div class="col-md-3">
                                    <h5>${user_employee.salary}$<br/>
                                        <small>Salary</small>
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </#if>
                </div>
            </div>
            <div class="col-lg-8 col-md-7">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Edit Profile</h4>
                    </div>
                    <div class="content">
                        <@form.form action="/user/update" id="form_user_update" method="post" modelAttribute="personProfileForm">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Company</label>
                                        <input type="text" class="form-control border-input" disabled
                                               placeholder="Company" value="Zakirskikh Inc.">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Email address</label>
                                        <@form.input path="email" type="email" class="form-control border-input" placeholder="Email"
                                               value="${user.email}" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>First Name</label>
                                        <@form.input path="firstName" type="text" class="form-control border-input" placeholder="Company"
                                               value="${user.firstName}" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Last Name</label>
                                        <@form.input path="lastName" type="text" class="form-control border-input" placeholder="Last Name"
                                               value="${user.lastName}" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <@form.select path="genderId" type="submit" class="form-control border-input"
                                                placeholder="Select gender" value="">
                                            <option value="0" ${user_male?then('selected','')}>Male</option>
                                            <option value="1" ${user_male?then('','selected')}>Female</option>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Passport ID</label>
                                        <@form.input path="passportId" type="text" class="form-control border-input" placeholder="Passport ID"
                                               value="${user.passportId}" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Address</label>
                                        <@form.input path="address" type="text" class="form-control border-input" placeholder="Home Address"
                                               value="${user.getAddress().address}" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>City</label>
                                        <@form.input path="city" type="text" class="form-control border-input" placeholder="City"
                                               value="${user.getAddress().city}" />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Country</label>
                                        <@form.input path="country" type="text" class="form-control border-input" placeholder="Country"
                                               value="${user.getAddress().country}" />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Post Code</label>
                                        <@form.input path="postcode" type="number" class="form-control border-input" placeholder="ZIP Code"
                                               value="${user.getAddress().postcode}" />
                                    </div>
                                </div>
                            </div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-info btn-fill btn-wd">Update Profile</button>
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