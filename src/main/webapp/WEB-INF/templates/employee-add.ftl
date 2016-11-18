<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <div class="content">
                        <@form.form action="/employees/add" id="form_employee_add" method="post" modelAttribute="employeeForm" >
                            <@form.input path="id" type="hidden" class="form-control border-input hidden" placeholder="Id" />
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
                                        <@form.input path="email" type="email" class="form-control border-input" placeholder="Email" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>First Name</label>
                                        <@form.input path="firstName" type="text" class="form-control border-input" placeholder="First Name" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Last Name</label>
                                        <@form.input path="lastName" type="text" class="form-control border-input" placeholder="Last Name" value="" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Post</label>
                                        <@form.select path="postId" type="submit" class="form-control border-input" placeholder="Select post" value="">
                                            <#list posts as post>
                                                <option value="${post.id?c}" <#if employeeForm.getPostId() == post.id>selected</#if>>${post.name}</option>
                                            </#list>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Hotel</label>
                                        <@form.select path="hotelId" type="submit" class="form-control border-input" placeholder="Select hotel" value="">
                                            <#list hotels as hotel>
                                                <option value="${hotel.id?c}" <#if employeeForm.getHotelId() == hotel.id>selected</#if>>${hotel.name}</option>
                                            </#list>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Salary</label>
                                        <@form.input path="salary" type="text" class="form-control border-input" placeholder="Salary" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Phone number</label>
                                        <@form.input path="phoneNumber" type="text" class="form-control border-input" placeholder="Phone number" value="" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <@form.select path="genderId" type="submit" class="form-control border-input" placeholder="Select gender" value="">
                                            <option value="0" <#if employeeForm.getGenderId() == 0>selected</#if>>Male</option>
                                            <option value="1" <#if employeeForm.getGenderId() == 1>selected</#if>>Female</option>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Passport ID</label>
                                        <@form.input path="passportId" type="text" class="form-control border-input" placeholder="Passport ID" value="" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Address</label>
                                        <@form.input path="address" type="text" class="form-control border-input" placeholder="Home Address" value="" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>City</label>
                                        <@form.input path="city" type="text" class="form-control border-input" placeholder="City" value="" />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Country</label>
                                        <@form.input path="country" type="text" class="form-control border-input" placeholder="Country" value="" />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Post Code</label>
                                        <@form.input path="postcode" type="number" class="form-control border-input" placeholder="ZIP Code" />
                                    </div>
                                </div>
                            </div>


                            <div class="text-center">
                                <#if is_edit??>
                                    <button type="submit" class="btn btn-info btn-fill btn-wd">Save</button>
                                <#else>
                                    <button type="submit" class="btn btn-info btn-fill btn-wd">Add new</button>
                                </#if>
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
