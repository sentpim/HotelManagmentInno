<#include "template.ftl"/>
<#macro m_body>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Bookings</h4>
                        <div class="text-center">
                            <a href="/bookings/add">
                                <button type="submit" class="btn btn-info btn-fill btn-wd">Add new</button>
                            </a>
                        </div>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-striped">
                            <thead>
                                <th>ID</th>
                                <th>Customer</th>
                                <th>Check in</th>
                                <th>Check out</th>
                                <th>Days</th>
                                <th>Payed</th>
                                <th>Persons</th>
                                <th>Action</th>
                            </thead>
                            <tbody>
                                <#list bookings as booking>
                                    <tr>
                                        <td>${booking.id}</td>
                                        <td>${booking.getPersonId()}: ${booking.getPerson().firstName} ${booking.getPerson().lastName}</td>
                                        <td>${booking.checkIn}</td>
                                        <td>${booking.checkOut}</td>
                                        <td>${booking.days}</td>
                                        <td>${booking.getIsPayed()}</td>
                                        <td>${booking.personCount}</td>
                                        <td>
                                            <a href="/bookings/${booking.id?c}/edit">Edit </a>
                                            |
                                            <a href="/bookings/${booking.id?c}/delete"> Delete </a>
                                            <#if booking.isPayed() == false>
                                                |
                                                <a href="/bookings/${booking.id?c}/pay"> Pay</a>
                                            </#if>
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
