<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                  <div class="content">
                        <div class="content">
                            <@form.form action="/bookings/add" id="form_user_update" method="post" modelAttribute="bookingForm">
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label>Check in</label>
                                            <@form.input path="checkIn" type="text" class="form-control border-input" placeholder="Check in" value="" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Nights</label>
                                            <@form.input path="days" type="text" class="form-control border-input" placeholder="Nights" value="" />
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Persons</label>
                                            <@form.input path="personId" type="email" class="form-control border-input" placeholder="Count" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Payed by cash</label>
                                            <@form.select path="isPayed" class="form-control border-input">
                                                <option value="0">True</option>
                                                <option value="1">False</option>
                                            </@form.select>    
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Hotel ID</label>
                                            <@form.select path="roomTypeId" class="form-control border-input">
                                                <#list hotels as hotel>
                                                    <option value="${hotel.id}">${hotel.name}</option>
                                                </#list>
                                            </@form.select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Room type</label>
                                            <@form.select path="roomTypeId" class="form-control border-input">
                                                <#list roomTypes as roomType>
                                                    <option value="${roomType.id}">${roomType.name}</option>
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
