<#include "template.ftl"/>
<#macro m_body>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Hotels</h4>
                                <div class="text-center">
                                    <a href="/hotels/add">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">Add new</button>
                                    </a>
                                    <a href="/roomtypes/add">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">Add Room Type</button>
                                    </a>
                                </div>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Budget</th>
                                        <th>City</th>
                                        <th>Residents</th>
                                        <th>Add</th>
                                        <th>Manage</th>
                                    </thead>
                                    <tbody>
                                        <#list hotels as hotel>
                                            <tr>
                                                <td>${hotel.id}</td>
                                                <td>${hotel.name}</td>
                                                <td>${hotel.budget}$</td>
                                                <td>${hotel.getAddress().city}</td>
                                                <td>${hotel.getResidentsCount()}</td>
                                                <td>
                                                    <a href="/hotels/${hotel.id}/rooms/add">Room </a>
                                                    <#--|-->
                                                    <#--<a href="/hotels/${hotel.id}/roomtypes/add"> Room type </a>-->
                                                    <#--|-->
                                                    <#--<a href="/hotels/${hotel.id}/features/add"> Feature </a>-->
                                                </td>
                                                <td>
                                                    <a href="/hotels/${hotel.id}/rooms"> Rooms</a>
                                                    |
                                                    <a href="/hotels/${hotel.id}/bookings"> Bookings </a>
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
