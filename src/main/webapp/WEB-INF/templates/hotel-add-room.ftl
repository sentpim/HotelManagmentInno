<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <div class="content">
                        <@form.form action="/hotels/${hotel.id?c}/rooms/add" id="form_room_add" method="post" modelAttribute="roomForm">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Number</label>
                                        <@form.input path="number" type="text" class="form-control border-input" placeholder="Number" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Room type</label>
                                        <@form.select path="roomTypeId" type="submit" class="form-control border-input">
                                            <#list roomTypes as roomType>
                                                <option value="${roomType.id?c}">${roomType.name}</option>
                                            </#list>
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
