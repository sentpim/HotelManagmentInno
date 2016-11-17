<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <div class="content">
                        <@form.form action="/roomtypes/add" id="form_roomtype_add" method="post" modelAttribute="roomTypeForm">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <@form.input path="name" type="text" class="form-control border-input" placeholder="Name" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Price</label>
                                        <@form.input path="price" type="text" class="form-control border-input" placeholder="Price" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Beds Count</label>
                                        <@form.input path="bedsCount" type="text" class="form-control border-input" placeholder="Beds Count" value="" />
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
