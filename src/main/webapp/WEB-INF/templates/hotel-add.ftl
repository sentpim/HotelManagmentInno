<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <div class="content">
                        <@form.form action="/hotels/add" id="form_hotel_add" method="post" modelAttribute="hotelForm">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <@form.input path="name" type="text" class="form-control border-input" placeholder="Name" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Budget</label>
                                        <@form.input path="budget" type="text" class="form-control border-input" placeholder="Budget" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Address</label>
                                        <@form.input path="address" type="text" class="form-control border-input" placeholder="Address" value="" />
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
                                        <label>City</label>
                                        <@form.input path="city" type="text" class="form-control border-input" placeholder="City" value="" />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Postcode</label>
                                        <@form.input path="postcode" type="text" class="form-control border-input" placeholder="Postcode" value="" />
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
