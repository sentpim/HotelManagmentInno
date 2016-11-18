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
                            <@form.input path="id" type="hidden" class="form-control border-input hidden" placeholder="Id" value="" />
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Customer</label>
                                        <@form.select path="personId" class="form-control border-input">
                                            <#list persons as person>
                                                <option value="${person.id?c}" <#if person.id == bookingForm.getPersonId()>selected</#if>>${person.id}
                                                    : ${person.firstName} ${person.lastName}</option>
                                            </#list>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Hotel</label>
                                        <@form.select path="hotelId" class="form-control border-input">
                                            <#list hotels as hotel>
                                                <option value="${hotel.id?c}" <#if hotel.id == bookingForm.getHotelId()>selected</#if>>${hotel.name}</option>
                                            </#list>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Check in(format: "YYYY-MM-DD")</label>
                                        <@form.input path="checkIn" type="text" class="form-control border-input" placeholder="Check in" value="" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Nights</label>
                                        <@form.input path="days" type="text" class="form-control border-input" placeholder="Nights" value="" />
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Payed by cash</label>
                                        <@form.select path="isPayed" class="form-control border-input">
                                            <option value="1" <#if bookingForm.getIsPayed() == true>selected</#if>>True</option>
                                            <option value="0" <#if bookingForm.getIsPayed() == false>selected</#if>>False</option>
                                        </@form.select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Person Count</label>
                                        <@form.input path="personCount" class="form-control border-input" placeholder="Person count" value=""/>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Room type</label>
                                        <@form.select path="roomTypeId" class="form-control border-input">
                                            <#list roomTypes as roomType>
                                                <option value="${roomType.id?c}" <#if roomType.id == bookingForm.getRoomTypeId()>selected</#if>>${roomType.name} - ${roomType.price}$</option>
                                            </#list>
                                        </@form.select>
                                    </div>
                                </div>
                            </div>

                            <div class="text-center">
                                <#if bookingForm.getId() != 0>
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
